package com.hjw.sandbox.utils;

import com.hjw.model.dto.sandbox.ExecuteMessage;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessUtils
{
    public static ExecuteMessage runProcessAndGetMessage(Process process, String operateName) throws Exception
    {
        ExecuteMessage executeMessage = new ExecuteMessage();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 获取程序执行退出 的状态码
        // 等待执行获取输出结果，真正执行过程在下面的方法中
        int exitValue = process.waitFor();
        StringBuilder compileOutputStringBuilder = new StringBuilder();
        StringBuilder compileErrorOutputStringBuilder = new StringBuilder();

        if (exitValue == 0)
        {
            System.out.println(operateName + "成功");
            // 获取正常结果
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String compileLine;
            while ((compileLine = bufferedReader.readLine()) != null)
            {
                compileOutputStringBuilder.append(compileLine).append("\n");
            }
        }
        else
        {
            // 异常退出
            System.out.println(operateName + "失败，错误码: " + exitValue);
            // 获取正常结果
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String compileLine;
            while ((compileLine = bufferedReader.readLine()) != null)
            {
                compileOutputStringBuilder.append(compileLine).append("\n");
            }

            // 获取错误结果
            InputStream inputErrorStream = process.getErrorStream();
            BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(inputErrorStream));
            String errorCompileLine;
            while ((errorCompileLine = errorBufferedReader.readLine()) != null)
            {
                compileErrorOutputStringBuilder.append(errorCompileLine).append("\n");
            }
        }
        // 程序计时
        stopWatch.stop();
        executeMessage.setTime(stopWatch.getLastTaskTimeMillis());

        executeMessage.setExitValue(exitValue);
        executeMessage.setMessage(compileOutputStringBuilder.toString());
        executeMessage.setErrorMessage(compileErrorOutputStringBuilder.toString());


        return executeMessage;
    }
}
