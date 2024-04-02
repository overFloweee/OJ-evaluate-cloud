package com.hjw.question.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Component
public class MyMessageProducer
{
    @Resource
    private RabbitTemplate rabbitTemplate;


    public void sendMessage(String exchange, String routingKey, String message)
    {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }


    // @PostConstruct
    // public void enableConfirmCallback()
    // {
    //     // confirm 监听，当消息成功发到交换机 ack = true，没有发送到交换机 ack = false
    //     // correlationData 可在发送时指定消息唯一 id
    //     rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
    //     {
    //         if (!ack)
    //         {
    //             // 记录日志、发送邮件通知、落库定时任务扫描重发
    //         }
    //     });
    //
    //     // 当消息成功发送到交换机没有路由到队列触发此监听
    //     rabbitTemplate.setReturnsCallback(returned ->
    //     {
    //         // 记录日志、发送邮件通知、落库定时任务扫描重发
    //     });
    // }
}
