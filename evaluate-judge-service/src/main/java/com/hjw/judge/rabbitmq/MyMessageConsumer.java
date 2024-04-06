package com.hjw.judge.rabbitmq;

import com.hjw.judge.service.JudgeService;
import com.hjw.model.entity.QuestionSubmit;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MyMessageConsumer
{

    @Resource
    private JudgeService judgeService;


    // 指定程序 监听的 消息队列和确认机制
    @SneakyThrows
    @RabbitListener(queues = {"code_queue"}, ackMode = "MANUAL")  // ack 手动确认模式
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
    {
        log.info("receiveMessage message = {}", message);

        long questionSubmitId = Long.parseLong(message);
        try
        {
            // 消费 questionSubmitId
            QuestionSubmit questionSubmit = judgeService.doJudge(questionSubmitId);
            // 判题失败，
            if ("{}".equals(questionSubmit.getJudgeInfo()))
            {
                // 第三个参数，是代表是否 该该消息重新排队
                channel.basicNack(deliveryTag,false,true);
            }
            // 用于确认已经接收并处理了消息 ack
            channel.basicAck(deliveryTag, false);
        }
        catch (Exception e)
        {
            channel.basicNack(deliveryTag,false,true);
        }

    }
}
