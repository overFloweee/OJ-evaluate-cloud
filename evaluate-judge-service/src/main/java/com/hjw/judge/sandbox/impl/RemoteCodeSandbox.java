package com.hjw.judge.sandbox.impl;

import com.hjw.api.service.CodeSandboxFeignClient;
import com.hjw.api.service.UserFeignClient;
import com.hjw.common.common.ErrorCode;
import com.hjw.common.exception.BusinessException;
import com.hjw.judge.sandbox.CodeSandbox;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 远程调用代码的沙箱
 */
@Component
@RequiredArgsConstructor
public class RemoteCodeSandbox implements CodeSandbox
{

    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_SECRET = "secretKey";
    private final CodeSandboxFeignClient codeSandboxFeignClient;

    private final UserFeignClient userFeignClient;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        // System.out.println("调用远程代码沙箱");
        // String url = "http://localhost:8081/executeCode";
        // // 携带参数
        // String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);
        //
        // // 发送请求，携带请求头
        // HttpResponse httpResponse = HttpUtil.createPost(url).header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
        //         .body(jsonStr).execute();
        //
        // String body = httpResponse.body();


        // 携带参数

        ExecuteCodeResponse executeCodeResponse = codeSandboxFeignClient.executeCode(executeCodeRequest,
                AUTH_REQUEST_SECRET
        );


        if (executeCodeResponse == null)
        {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,
                    "execute remotesanbox error,message = " + executeCodeResponse
            );
        }

        return executeCodeResponse;
    }
}
