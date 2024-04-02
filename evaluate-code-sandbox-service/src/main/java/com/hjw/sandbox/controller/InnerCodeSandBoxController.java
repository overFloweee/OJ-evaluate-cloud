package com.hjw.sandbox.controller;

import cn.hutool.json.JSONUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.sandbox.service.dockerimpl.JavaDockerCodeSandbox;
import com.hjw.sandbox.service.nativeimpl.JavaNativeCodeSandbox;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/inner")
public class InnerCodeSandBoxController
{

    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Resource
    private JavaDockerCodeSandbox javaDockerCodeSandbox;
    @Resource
    private JavaNativeCodeSandbox javaNativeCodeSandbox;


    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest,
                                           @RequestHeader(value = "auth") String auth, HttpServletResponse response
    )
    {

        if (!AUTH_REQUEST_SECRET.equals(auth))
        {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null)
        {
            throw new RuntimeException("请求参数为空");
        }


        String osName = System.getProperty("os.name");
        System.out.println("操作系统是：" + osName);
        if (osName.contains("Linux"))
        {
            return javaDockerCodeSandbox.executeCode(executeCodeRequest);
        }
        return javaNativeCodeSandbox.executeCode(executeCodeRequest);
    }

    @GetMapping("/test")
    public String test()
    {
        // 连接docker服务器
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.60.200:2375").build();

        // 获取服务器信息
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSONUtil.toJsonStr(info);
        System.out.println(infoStr);
        return infoStr;
    }


}
