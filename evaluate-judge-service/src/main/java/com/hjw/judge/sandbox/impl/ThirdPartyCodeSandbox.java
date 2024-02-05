package com.hjw.judge.sandbox.impl;


import com.hjw.judge.sandbox.CodeSandbox;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * 第三方 代码沙箱
 */
@Component
public class ThirdPartyCodeSandbox implements CodeSandbox
{

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        return null;
    }
}
