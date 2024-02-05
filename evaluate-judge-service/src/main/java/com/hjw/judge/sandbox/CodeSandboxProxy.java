package com.hjw.judge.sandbox;


import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱 代理
 * 增强功能
 */
@AllArgsConstructor
@Slf4j
public class CodeSandboxProxy implements CodeSandbox
{

    private final CodeSandbox codeSandbox;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        log.info("代码沙箱请求信息:" + executeCodeRequest);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息:" + executeCodeResponse);
        return executeCodeResponse;


    }

}
