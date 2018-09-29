package com.example.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * 创建时间 2018年九月27日 星期四 20:42
 * 作者: zhangjunping
 */
@Component
public class Consumer {

    @JmsListener(destination = "${queue}",containerFactory = "queueListenerFactory")
    public void receive(String message){
        System.out.println("监听器收到msg:" + message + Thread.currentThread().getName());
    }

    @JmsListener(destination = "${topic}",containerFactory = "topicListenerFactory")
    public void receiveTopic(String message){
        System.out.println("监听器收到msg:" + message + Thread.currentThread().getName());
    }
}
