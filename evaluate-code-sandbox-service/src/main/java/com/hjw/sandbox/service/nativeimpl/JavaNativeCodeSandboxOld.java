package com.hjw.sandbox.service.nativeimpl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.model.dto.sandbox.ExecuteMessage;
import com.hjw.model.dto.sandbox.JudgeInfo;
import com.hjw.sandbox.service.CodeSandbox;
import com.hjw.sandbox.utils.ProcessUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Deprecated
public class JavaNativeCodeSandboxOld implements CodeSandbox
{

    private static final String GLOBAL_CODE_DIR_NAME = "tempCode";
    private static final String DEFAULT_JAVA_CLASS_NAME = "Main.java";

    public static void main(String[] args) throws Exception
    {
        CodeSandbox javaNativeCodeSandbox = new JavaNativeCodeSandbox();

        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2", "3 4"));
        String code = ResourceUtil.readStr("Main.java", StandardCharsets.UTF_8);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");


        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
        System.out.println("整个程序用例的返回结果：" + executeCodeResponse);

    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) throws Exception, InterruptedException
    {
        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();

        String userDir = System.getProperty("user.dir");
        String globalTempFileLocation = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断 全局代码目录是否存在
        if (!FileUtil.exist(globalTempFileLocation))
        {
            FileUtil.mkdir(globalTempFileLocation);
        }

        // 把用户的代码隔离存放
        String userCodeDirPath = globalTempFileLocation + File.separator + UUID.randomUUID();
        String userCodePath = userCodeDirPath + File.separator + DEFAULT_JAVA_CLASS_NAME;
        // 1. 将用户提交代码 输出成 文件
        File userCodeFile = FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);


        // 2. 编译 .java 文件，得到 .class 文件
        // 不使用绝对路径，谨防其中含有中文,javac的路径不能含有中文
        List<String> splitList = StrUtil.split(userCodePath, "\\");
        String relativeCompilePath = splitList.get(splitList.size() - 3) + File.separator + splitList.get(
                splitList.size() - 2) + File.separator + splitList.get(splitList.size() - 1);
        try
        {
            // 执行编译
            String compileCmd = String.format("javac  -encoding utf-8  -cp . %s", relativeCompilePath);
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            // 获取编译的 输出信息
            ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
            System.out.println(executeMessage);
        }
        catch (Exception e)
        {
            //  错误处理，提高程序健壮性
            return getResponse(e);
        }

        // 3. 执行代码，得到输出结果
        ArrayList<ExecuteMessage> executeRunMessageList = new ArrayList<>();

        String runPath = relativeCompilePath.substring(0, relativeCompilePath.length() - 9);
        for (String inputStr : inputList)
        {
            String runCmd = String.format("java -Dfile.encoding=utf-8 -cp %s Main %s", runPath, inputStr);
            try
            {
                // 执行
                Process runProcess = Runtime.getRuntime().exec(runCmd);
                // 等待执行获取输出结果，真正执行过程在下面的方法中
                ExecuteMessage executeRunMessage = ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
                executeRunMessageList.add(executeRunMessage);
                System.out.println(executeRunMessage);

            }
            catch (Exception e)
            {
                //  错误处理，提高程序健壮性
                return getResponse(e);
            }
        }

        // 4. 收集、整理输出结果
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        ArrayList<String> outputList = new ArrayList<>();
        long maxTime = 0;
        for (ExecuteMessage message : executeRunMessageList)
        {
            String errorMessage = message.getErrorMessage();
            // 程序中存在错误
            if (StrUtil.isNotBlank(errorMessage))
            {
                executeCodeResponse.setMessage(errorMessage);
                // 3 - 用户代码运行存在错误
                executeCodeResponse.setStatus(3);
                break;
            }
            outputList.add(message.getMessage());
            // 取多次用例执行的最大值
            Long time = message.getTime();
            if (time != null)
            {
                maxTime = Math.max(maxTime, time);
            }
        }

        executeCodeResponse.setOutputList(outputList);
        if (outputList.size() == executeRunMessageList.size())
        {
            // 1 - 正常
            executeCodeResponse.setStatus(1);
        }
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(maxTime);
        // todo 获取内存需要使用到第三方库，非常麻烦
        judgeInfo.setMemory(null);
        executeCodeResponse.setJudgeInfo(judgeInfo);


        // 5. 文件清理
        if (userCodeFile.getParentFile() != null)
        {
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
    private ExecuteCodeResponse getResponse(Throwable e)
    {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(new ArrayList<>());
        executeCodeResponse.setMessage(e.getMessage());
        // 2 - 代码沙箱错误
        executeCodeResponse.setStatus(2);
        executeCodeResponse.setJudgeInfo(new JudgeInfo());

        return executeCodeResponse;
    }
}
