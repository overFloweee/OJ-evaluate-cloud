package com.hjw.api.service;

import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "evaluate-code-sandbox-service", path = "/api/codesanbox/inner")
public interface CodeSandboxFeignClient
{
    @PostMapping("/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest,
                                    @RequestHeader(value = "auth") String auth
    );

}
