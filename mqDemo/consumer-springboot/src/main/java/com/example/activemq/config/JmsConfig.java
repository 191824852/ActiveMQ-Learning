package com.example.activemq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import java.util.concurrent.Executors;

/**
 * 创建时间 2018年九月29日 星期六 10:49
 * 作者: zhangjunping
 */
@Configuration
@EnableJms
public class JmsConfig {

    /**
     * 实例化发布订阅模式工厂
     * @param connectionFactory 链接工厂
     * @return 返回发布订阅模式工厂
     */
    @Bean
    public JmsListenerContainerFactory topicListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(Boolean.TRUE);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    /**
     * 实例化点对点模式工厂
     * @param connectionFactory 链接工厂
     * @return 返回点对点模式工厂
     */
    @Bean
    public JmsListenerContainerFactory queueListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(Boolean.FALSE);
//        如想并发消费请配置线程池
        factory.setTaskExecutor(Executors.newFixedThreadPool(10));
        factory.setConcurrency("10");

        factory.setConnectionFactory(connectionFactory);
        return factory;
    }


}
