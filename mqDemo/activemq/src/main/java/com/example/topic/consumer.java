package com.example.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建时间 2018年九月27日 星期四 20:09
 * 作者: zhangjunping
 */
public class consumer {
    private final static String URL = "tcp://localhost:61616";
    private final static String TOPICNAME = "my_topic";
    public static void main(String[] args) throws  JMSException {
        // 1.创建ActiveMQFactory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        // 2.创建连接
        Connection connection = factory.createConnection();
        // 3.启动连接
        connection.start();
        // 4.创建Session 不开启事务,自动签收模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5.创建一个目标
        Topic topic = session.createTopic(TOPICNAME);
        // 6.创建生产者
        MessageConsumer createConsumer = session.createConsumer(topic);
        createConsumer.setMessageListener(message -> {
            TextMessage msg = (TextMessage) message;
            try {
                System.out.println("消费者消费消息：" + msg.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
