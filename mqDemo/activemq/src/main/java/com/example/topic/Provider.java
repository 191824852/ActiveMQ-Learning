package com.example.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建时间 2018年九月27日 星期四 19:58
 * 作者: zhangjunping
 * 生产者
 */
public class Provider {
    /**
     * mq通讯地址
     */
    private final static String URL = "tcp://localhost:61616";
    private final static String TOPICNAME = "my_topic";
    public static void main(String[] args) throws JMSException {
        // 1.创建ActiveMQFactory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        // 2.创建连接
        Connection connection = factory.createConnection();
        // 3.启动连接
        connection.start();
        // 4.创建Session 不开启事务,自动签收模式
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 5.创建一个目标
        Topic createTopic = session.createTopic(TOPICNAME);
        // 6.创建生产者
        MessageProducer producer = session.createProducer(createTopic);
        // 设置消息持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i = 1; i <= 10; i++) {
            // 7.创建消息
            TextMessage textMessage = session.createTextMessage("消息" + i);
            // 8.发送消息
            producer.send(textMessage);
            System.out.println(textMessage.getText());
        }
        // 9.关闭连接
        connection.close();
    }
}
