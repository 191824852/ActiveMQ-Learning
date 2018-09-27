package com.example.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 创建时间 2018年九月27日 星期四 20:42
 * 作者: zhangjunping
 */
@Component
public class Consumer {

    @JmsListener(destination = "${queue}")
    public void receive(String message){
        System.out.println("监听器收到msg:" + message);
    }
}
