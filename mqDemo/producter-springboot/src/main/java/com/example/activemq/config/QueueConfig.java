package com.example.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * 创建时间 2018年九月27日 星期四 20:34
 * 作者: zhangjunping
 */
@Configuration
public class QueueConfig {

    @Value("${queue}")
    private String queueName;

    @Bean
    public Queue initLogQueue() {
        return new ActiveMQQueue(queueName);
    }
}
