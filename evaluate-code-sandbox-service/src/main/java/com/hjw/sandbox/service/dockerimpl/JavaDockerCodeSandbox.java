package com.hjw.sandbox.service.dockerimpl;

import cn.hutool.core.util.ArrayUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.model.dto.sandbox.ExecuteMessage;
import com.hjw.sandbox.service.JavaCodeSandboxTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JavaDockerCodeSandbox extends JavaCodeSandboxTemplate
{

    private static final long TIME_OUT = 5000L;

    private static final String IMAGE = "openjdk:8-alpine";

    private static DockerClient dockerClient;

    private static boolean isLoad = false;

    private static final long[] maxMemory = {0L};


    /**
     * 3. 创建一个可交互的容器，在容器内执行代码，得到输出结果
     *
     * @param userCodeFile Main.java 文件
     * @param inputList    程序输入用例
     * @return
     */
    @Override
    public List<ExecuteMessage> executeFile(File userCodeFile, List<String> inputList)
    {
        log.info("docker执行");
        if (!isLoad)
        {
            dockerClient = DockerClientBuilder.getInstance().build();
            PullImageCmd pullImageCmd = dockerClient.pullImageCmd(IMAGE);
            PullImageResultCallback pullImageResultCallback = new PullImageResultCallback()
            {
                @Override
                public void onNext(PullResponseItem item)
                {
                    System.out.println("下载镜像层： " + item.getStatus());
                }
            };
            try
            {
                pullImageCmd.exec(pullImageResultCallback).awaitCompletion();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

            System.out.println("镜像下载完成！");
            isLoad = true;
        }

        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(IMAGE);
        // 路径挂载，双向绑定
        HostConfig hostConfig = new HostConfig();
        hostConfig.withMemory(100 * 1000 * 1000L);  // 100MB
        hostConfig.withCpuCount(1L);
        hostConfig.withReadonlyRootfs(true);
        hostConfig.setBinds(new Bind(userCodeFile.getParentFile().toString(), new Volume("/app")));
        CreateContainerResponse response = containerCmd.withAttachStdin(true)  // 连接输入和输出
                .withAttachStderr(true).withAttachStdout(true).withTty(true)   // 可交互终端
                .withHostConfig(hostConfig)       // 配置
                .withNetworkDisabled(true)  // 容器内部禁用网络
                .exec();

        String containerId = response.getId();
        //      启动容器
        dockerClient.startContainerCmd(containerId).exec();
        //      和容器交互
        //      docker exec keen_blackwell java -cp /app -Dfile.encoding=utf-8  Main 1 3
        ArrayList<ExecuteMessage> executeMessageList = new ArrayList<>();

        for (String input : inputList)
        {
            String[] inputArgsList = input.split(" ");
            String[] cmdArray = ArrayUtil.append(new String[]{"java", "-cp", "/app", "-Dfile.encoding=utf-8", "Main"},
                    inputArgsList
            );

            // 构建启动命令
            ExecCreateCmdResponse createCmdResponse = dockerClient.execCreateCmd(containerId).withCmd(cmdArray)
                    .withAttachStderr(true).withAttachStdin(true).withAttachStdout(true).exec();
            System.out.println("创建执行命令： " + createCmdResponse);
            String execId = createCmdResponse.getId();

            ExecuteMessage executeMessage = new ExecuteMessage();
            final String[] message = {null};
            final String[] errorMessage = {null};


            // 开启监控内存，是一个异步操作，获取占用的最大内存
            StatsCmd statsCmd = dockerClient.statsCmd(containerId);
            statsCmd.exec(new ResultCallback<Statistics>()
            {
                @Override
                public void onStart(Closeable closeable)
                {

                }

                @Override
                public void onNext(Statistics statistics)
                {
                    Long memory = statistics.getMemoryStats().getUsage();
                    maxMemory[0] = Math.max(maxMemory[0], memory);
                    System.out.println("容器内存占用： " + memory);
                }

                @Override
                public void onError(Throwable throwable)
                {

                }

                @Override
                public void onComplete()
                {

                }

                @Override
                public void close() throws IOException
                {

                }
            });

            // 执行启动命令
            ExecStartResultCallback execStartResultCallback = new ExecStartResultCallback()
            {
                @Override
                public void onNext(Frame frame)
                {
                    if (StreamType.STDERR.equals(frame.getStreamType()))
                    {
                        errorMessage[0] = new String(frame.getPayload());
                        System.out.println("执行命令输出错误结果： " + errorMessage[0]);
                    }
                    else
                    {
                        message[0] = new String(frame.getPayload());
                        System.out.println("执行命令输出结果： " + message[0]);
                    }
                }
            };
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            boolean isNotTimeOut = true;
            try
            {
                isNotTimeOut = dockerClient.execStartCmd(execId).exec(execStartResultCallback)
                        .awaitCompletion(TIME_OUT, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            stopWatch.stop();
            long time = stopWatch.getLastTaskTimeMillis();
            statsCmd.close();
            if (!isNotTimeOut)
            {
                System.out.println("执行启动命令超时 ");
            }
            else
            {
                System.out.println("执行启动完毕 ");
            }


            executeMessage.setMessage(message[0]);
            executeMessage.setErrorMessage(errorMessage[0]);
            executeMessage.setTime(time);
            executeMessage.setMemory(maxMemory[0]);
            executeMessageList.add(executeMessage);
        }

        return executeMessageList;

    }


    /**
     * 4. 收集、整理输出结果
     * 此处使用了docker 进行内存监控，获取最大值内存
     *
     * @param executeRunMessageList 程序执行信息集合
     * @return
     */
    @Override
    public ExecuteCodeResponse getOutputResponse(List<ExecuteMessage> executeRunMessageList)
    {
        ExecuteCodeResponse outputResponse = super.getOutputResponse(executeRunMessageList);
        // 设置最大内存
        outputResponse.getJudgeInfo().setMemory(maxMemory[0]);

        return outputResponse;
    }
}
