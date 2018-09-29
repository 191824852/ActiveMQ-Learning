package com.example.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 创建时间 2018年九月27日 星期四 20:34
 * 作者: zhangjunping
 */
@Configuration
public class QueueConfig {

    @Value("${queue}")
    private String queueName;

    @Value("${msgQueueName}")
    private String msgQueueName;


    @Value("${topic}")
    private String topicName;

//    点对点消息队列
    @Bean
    public Queue initLogQueue() {
        return new ActiveMQQueue(queueName);
    }

    @Bean
    public Queue initMsgQueue(){return new ActiveMQQueue(msgQueueName);}



//   发布订阅主题
    @Bean
    public Topic initLogTopic(){
        return new ActiveMQTopic(topicName);
    }

}
