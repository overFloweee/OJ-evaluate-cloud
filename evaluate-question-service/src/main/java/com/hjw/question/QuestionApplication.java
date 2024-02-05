package com.hjw.question;

import com.hjw.api.config.FeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.hjw")
@MapperScan("com.hjw.question.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableFeignClients(basePackages = "com.hjw.api.service")   // 开启openfeign支持
public class QuestionApplication
{


    public static void main(String[] args)
    {
        SpringApplication.run(QuestionApplication.class, args);
    }
}
