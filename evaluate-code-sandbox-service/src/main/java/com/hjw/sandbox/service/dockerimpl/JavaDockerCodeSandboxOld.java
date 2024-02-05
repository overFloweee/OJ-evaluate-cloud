package com.hjw.sandbox.service.dockerimpl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.model.dto.sandbox.ExecuteMessage;
import com.hjw.model.dto.sandbox.JudgeInfo;
import com.hjw.sandbox.service.CodeSandbox;
import com.hjw.sandbox.utils.ProcessUtils;
import org.springframework.util.StopWatch;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Deprecated
public class JavaDockerCodeSandboxOld implements CodeSandbox
{

    private static final String GLOBAL_CODE_DIR_NAME = "tempCode";
    private static final String DEFAULT_JAVA_CLASS_NAME = "Main.java";
    private static final long TIME_OUT = 5000L;

    private static final String IMAGE = "openjdk:8-alpine";

    private static final DockerClient dockerClient = DockerClientBuilder.getInstance().build();

    static {
        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(IMAGE);
        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
            @Override
            public void onNext(PullResponseItem item) {
                System.out.println("下载镜像层： " + item.getStatus());
            }
        };
        try {
            pullImageCmd.exec(pullImageResultCallback).awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("镜像下载完成！");
    }


    public static void main(String[] args) throws Exception {
        JavaDockerCodeSandboxOld javaNativeCodeSandbox = new JavaDockerCodeSandboxOld();

        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2", "3 4"));
        String code = ResourceUtil.readStr("Main.java", StandardCharsets.UTF_8);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");


        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
        System.out.println("整个程序用例的返回结果：");
        System.out.println(executeCodeResponse);

    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) throws Exception, InterruptedException {


        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();

        String userDir = System.getProperty("user.dir");
        String globalTempFileLocation = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断 全局代码目录是否存在
        if (!FileUtil.exist(globalTempFileLocation)) {
            FileUtil.mkdir(globalTempFileLocation);
        }

        // 把用户的代码隔离存放
        String userCodeDirPath = globalTempFileLocation + File.separator + UUID.randomUUID();
        String userCodePath = userCodeDirPath + File.separator + DEFAULT_JAVA_CLASS_NAME;
        // 1. 将用户提交代码 输出成 文件
        File userCodeFile = FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);


        // 2. 编译 .java 文件，得到 .class 文件
        // 不使用绝对路径，谨防其中含有中文,javac的路径不能含有中文
        List<String> splitList = StrUtil.split(userCodePath, File.separator);
        String compilePath = splitList.get(splitList.size() - 3) + File.separator + splitList.get(
                splitList.size() - 2) + File.separator + splitList.get(splitList.size() - 1);
        try {
            // 执行编译
            String compileCmd = String.format("javac -encoding utf-8  -cp . %s", compilePath);
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            // 获取编译的 输出信息
            ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
            System.out.println(executeMessage);
        } catch (Exception e) {
            //  错误处理，提高程序健壮性
            return getResponse(e);
        }

        // 3. 创建一个可交互的容器，在容器内执行代码，得到输出结果
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(IMAGE);
        // 路径挂载，双向绑定
        HostConfig hostConfig = new HostConfig();
        hostConfig.withMemory(100 * 1000 * 1000L);  // 100MB
        hostConfig.withCpuCount(1L);
        hostConfig.withReadonlyRootfs(true);
        hostConfig.setBinds(new Bind(userCodeDirPath, new Volume("/app")));
        CreateContainerResponse response = containerCmd
                .withAttachStdin(true)  // 连接输入和输出
                .withAttachStderr(true)
                .withAttachStdout(true)
                .withTty(true)          // 可交互终端
                .withHostConfig(hostConfig)       // 配置
                .withNetworkDisabled(true)  // 容器内部禁用网络
                .exec();

        String containerId = response.getId();
        //      启动容器
        dockerClient.startContainerCmd(containerId).exec();
        //      和容器交互
        //      docker exec keen_blackwell java -cp /app -Dfile.encoding=utf-8  Main 1 3
        ArrayList<ExecuteMessage> executeMessageList = new ArrayList<>();
        final long[] maxMemory = {0L};

        for (String input : inputList) {
            String[] inputArgsList = input.split(" ");
            String[] cmdArray = ArrayUtil.append(new String[]{"java", "-cp", "/app", "-Dfile.encoding=utf-8", "Main"}, inputArgsList);

            // 构建启动命令
            ExecCreateCmdResponse createCmdResponse = dockerClient.execCreateCmd(containerId)
                    .withCmd(cmdArray)
                    .withAttachStderr(true)
                    .withAttachStdin(true)
                    .withAttachStdout(true)
                    .exec();
            System.out.println("创建执行命令： " + createCmdResponse);
            String execId = createCmdResponse.getId();

            ExecuteMessage executeMessage = new ExecuteMessage();
            final String[] message = {null};
            final String[] errorMessage = {null};


            // 开启监控内存，是一个异步操作，获取占用的最大内存
            StatsCmd statsCmd = dockerClient.statsCmd(containerId);
            statsCmd.exec(new ResultCallback<Statistics>() {
                @Override
                public void onStart(Closeable closeable) {

                }

                @Override
                public void onNext(Statistics statistics) {
                    Long memory = statistics.getMemoryStats().getUsage();
                    maxMemory[0] = Math.max(maxMemory[0], memory);
                    System.out.println("容器内存占用： " + memory);
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }

                @Override
                public void close() throws IOException {

                }
            });

            // 执行启动命令
            ExecStartResultCallback execStartResultCallback = new ExecStartResultCallback() {
                @Override
                public void onNext(Frame frame) {
                    if (StreamType.STDERR.equals(frame.getStreamType())) {
                        errorMessage[0] = new String(frame.getPayload());
                        System.out.println("执行命令输出错误结果： " + errorMessage[0]);
                    } else {
                        message[0] = new String(frame.getPayload());
                        System.out.println("执行命令输出结果： " + message[0]);
                    }
                }
            };
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            boolean isNotTimeOut = dockerClient.execStartCmd(execId).exec(execStartResultCallback).awaitCompletion(TIME_OUT, TimeUnit.MILLISECONDS);
            stopWatch.stop();
            long time = stopWatch.getLastTaskTimeMillis();
            statsCmd.close();
            if (!isNotTimeOut) {
                System.out.println("执行启动命令超时 ");
            }else {
                System.out.println("执行启动完毕 ");
            }


            executeMessage.setMessage(message[0]);
            executeMessage.setErrorMessage(errorMessage[0]);
            executeMessage.setTime(time);
            executeMessage.setMemory(maxMemory[0]);
            executeMessageList.add(executeMessage);
        }


        // 4. 收集、整理输出结果
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        ArrayList<String> outputList = new ArrayList<>();
        long maxTime = 0;
        for (ExecuteMessage message : executeMessageList) {
            String errorMessage = message.getErrorMessage();
            // 程序中存在错误
            if (StrUtil.isNotBlank(errorMessage)) {
                executeCodeResponse.setMessage(errorMessage);
                // 3 - 用户代码运行存在错误
                executeCodeResponse.setStatus(3);
                break;
            }
            outputList.add(message.getMessage());
            // 取多次用例执行的最大值
            Long time = message.getTime();
            if (time != null) {
                maxTime = Math.max(maxTime, time);
            }
        }

        executeCodeResponse.setOutputList(outputList);
        if (outputList.size() == executeMessageList.size()) {
            // 1 - 正常
            executeCodeResponse.setStatus(1);
        }
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(maxTime);
        // 设置内存
        judgeInfo.setMemory(maxMemory[0]);
        executeCodeResponse.setJudgeInfo(judgeInfo);


        // 5. 文件清理
        if (userCodeFile.getParentFile() != null) {
            boolean del = FileUtil.del(userCodeDirPath);
            System.out.println("删除" + (del ? "成功" : "失败"));
        }

        return executeCodeResponse;
    }

    /**
     * fallback
     *
     * @param e
     * @return
     */
    private ExecuteCodeResponse getResponse(Throwable e) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(new ArrayList<>());
        executeCodeResponse.setMessage(e.getMessage());
        // 2 - 代码沙箱错误
        executeCodeResponse.setStatus(2);
        executeCodeResponse.setJudgeInfo(new JudgeInfo());

        return executeCodeResponse;
    }
}
