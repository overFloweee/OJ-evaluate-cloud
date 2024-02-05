package com.hjw.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(scanBasePackages = "com.hjw")
@MapperScan("com.hjw.user.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableFeignClients(basePackages = "com.hjw.api.service")   // 开启openfeign支持
public class UserApplication
{


    public static void main(String[] args)
    {
        SpringApplication.run(UserApplication.class, args);
    }
}
