package com.hjw.sandbox.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.model.dto.sandbox.ExecuteMessage;
import com.hjw.model.dto.sandbox.JudgeInfo;
import com.hjw.model.enums.JudgeInfoEnum;
import com.hjw.sandbox.service.CodeSandbox;
import com.hjw.sandbox.utils.ProcessUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * java 原生实现，直接复用
 * 设计模式：模板方法  Temple
 */
@Slf4j
public class JavaCodeSandboxTemplate implements CodeSandbox
{

    private static final String GLOBAL_CODE_DIR_NAME = "tempCode";
    private static final String DEFAULT_JAVA_CLASS_NAME = "Main.java";


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();

        // 1. 将用户提交代码 输出成 文件
        File userCodeFile = saveCodeToFile(code);


        // 2. 编译 .java 文件，得到 .class 文件
        ExecuteMessage executeMessage = compileFile(userCodeFile);
        if (StrUtil.isNotEmpty(executeMessage.getErrorMessage()))
        {
            deleteFile(userCodeFile);
            ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
            JudgeInfo judgeInfo = new JudgeInfo();
            judgeInfo.setMessage(executeMessage.getErrorMessage());
            executeCodeResponse.setJudgeInfo(judgeInfo);
            return executeCodeResponse;
        }
        System.out.println("编译完成：" + executeMessage);


        // 3. 执行代码，得到输出结果
        List<ExecuteMessage> executeRunMessageList = executeFile(userCodeFile, inputList);
        System.out.println("执行完成");

        // 4. 收集、整理输出结果
        ExecuteCodeResponse outputResponse = getOutputResponse(executeRunMessageList);
        System.out.println("整理结果");

        // 5. 文件清理
        boolean isDel = deleteFile(userCodeFile);
        if (!isDel)
        {
            log.error("删除文件失败！userCodeFile = {}", userCodeFile.getAbsolutePath());
        }


        return outputResponse;
    }


    /**
     * 1. 将用户提交代码 输出成 文件
     *
     * @param code 用户代码
     * @return
     */
    public File saveCodeToFile(String code)
    {
        String userDir = System.getProperty("user.dir");
        System.out.println("userDir: " + userDir);
        String globalTempFileLocation = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断 全局代码目录是否存在
        if (!FileUtil.exist(globalTempFileLocation))
        {
            FileUtil.mkdir(globalTempFileLocation);
        }

        // 把用户的代码隔离存放
        String userCodeDirPath = globalTempFileLocation + File.separator + UUID.randomUUID();
        String userCodePath = userCodeDirPath + File.separator + DEFAULT_JAVA_CLASS_NAME;
        return FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
    }


    /**
     * 2. 编译 .java 文件，得到 .class 文件
     *
     * @param userCodeFile Main.java 文件
     * @return
     */
    public ExecuteMessage compileFile(File userCodeFile)
    {
        ExecuteMessage executeMessage;
        // 不使用绝对路径，谨防其中含有中文,javac的路径不能含有中文
        List<String> splitList = StrUtil.split(userCodeFile.toString(), File.separator);
        String compilePath = splitList.get(splitList.size() - 3) + File.separator + splitList.get(
                splitList.size() - 2) + File.separator + splitList.get(splitList.size() - 1);
        try
        {
            // 执行编译
            String compileCmd = String.format("javac  -encoding utf-8  -cp . %s", compilePath);
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            // 获取编译的 输出信息
            executeMessage = ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
            executeMessage.setErrorMessage(null);
            if (executeMessage.getExitValue() != 0)
            {
                executeMessage.setErrorMessage("Compile Error");
                // throw new RuntimeException("程序编译错误！");
            }
            System.out.println(executeMessage);
        }
        catch (Exception e)
        {
            //  错误处理，提高程序健壮性
            // return getResponse(e);
            throw new RuntimeException(e);

        }

        return executeMessage;
    }

    /**
     * 3. 执行代码，得到输出结果
     *
     * @param userCodeFile Main.java 文件
     * @param inputList    程序输入用例
     */
    public List<ExecuteMessage> executeFile(File userCodeFile, List<String> inputList)
    {
        System.out.println("开始执行代码");
        // 不使用绝对路径，谨防其中含有中文,javac的路径不能含有中文
        List<String> splitList = StrUtil.split(userCodeFile.toString(), File.separator);
        String relativeCompilePath = splitList.get(splitList.size() - 3) + File.separator + splitList.get(
                splitList.size() - 2) + File.separator + splitList.get(splitList.size() - 1);

        System.out.println("relativeCompilePath: " + relativeCompilePath);

        ArrayList<ExecuteMessage> executeRunMessageList = new ArrayList<>();

        String runPath = relativeCompilePath.substring(0, relativeCompilePath.length() - 9);
        System.out.println("runPath" + runPath);
        System.out.println("inputList" + inputList);
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
                // return getResponse(e);
                throw new RuntimeException("程序执行异常");
            }
        }
        return executeRunMessageList;
    }


    /**
     * 4. 收集、整理输出结果
     *
     * @param executeRunMessageList 程序执行信息集合
     * @return
     */
    public ExecuteCodeResponse getOutputResponse(List<ExecuteMessage> executeRunMessageList)
    {
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
        judgeInfo.setMemory(RandomUtil.randomLong(110, 130));
        executeCodeResponse.setJudgeInfo(judgeInfo);


        return executeCodeResponse;
    }


    private boolean deleteFile(File userCodeFile)
    {
        if (userCodeFile.getParentFile() != null)
        {
            boolean del = FileUtil.del(userCodeFile.getParentFile());
            System.out.println("删除" + (del ? "成功" : "失败"));
            return del;
        }

        return true;
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
