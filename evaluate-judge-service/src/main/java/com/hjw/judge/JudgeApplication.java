package com.hjw.judge;


import com.hjw.judge.rabbitmq.InitRabbitMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.hjw", "com.hjw.judge.sandbox"})
@EnableFeignClients(basePackages = "com.hjw.api.service")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class JudgeApplication
{

    @Resource
    private InitRabbitMq initRabbitMq;

    public static void main(String[] args)
    {
        // InitRabbitMq.doInit();
        SpringApplication.run(JudgeApplication.class, args);
    }

    @PostConstruct
    public void init()
    {
        initRabbitMq.doInit();
    }
}
