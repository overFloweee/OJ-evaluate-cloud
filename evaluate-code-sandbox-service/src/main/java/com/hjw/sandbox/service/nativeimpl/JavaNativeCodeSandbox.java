package com.hjw.sandbox.service.nativeimpl;

import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.sandbox.service.JavaCodeSandboxTemplate;
import org.springframework.stereotype.Component;


/**
 * 继承了模板方法
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate
{


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        return super.executeCode(executeCodeRequest);
    }
}
