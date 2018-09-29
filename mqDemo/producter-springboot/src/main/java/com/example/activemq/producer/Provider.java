package com.example.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;
import java.time.Instant;

/**
 * 创建时间 2018年九月27日 星期四 20:36
 * 作者: zhangjunping
 */
@Component
public class Provider {

    @Autowired
    private JmsMessagingTemplate template;

    @Resource(name = "logQueue")
    private Queue logQueue;

    @Resource(name = "msgQueue")
    private Queue msgQueue;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 5000)
    public void sendLog() {
        System.out.println("点对点模式（p2p） 生产者---logQueue:" +Instant.now());
        template.convertAndSend(logQueue, "点对点(p2p)---logQueue:" + Instant.now());
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMsg() {
        System.out.println("点对点模式（p2p） 生产者---msgQueue:" +Instant.now());
        template.convertAndSend(msgQueue, "点对点(p2p)---msgQueue:" + Instant.now());
    }


    @Scheduled(fixedDelay = 5000)
    public void sendTopic(){
        System.out.println("订阅模式（topic） 生产者：" + Instant.now());
        template.convertAndSend(topic, "订阅模式（topic）：" + Instant.now());
    }
}
