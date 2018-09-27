package com.example.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.time.Instant;

/**
 * 创建时间 2018年九月27日 星期四 20:36
 * 作者: zhangjunping
 */
@Component
public class Provider {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 5000)
    public void send() {
        System.out.println(Instant.now());
        template.convertAndSend(queue, "生产者:" + Instant.now());
    }
}
