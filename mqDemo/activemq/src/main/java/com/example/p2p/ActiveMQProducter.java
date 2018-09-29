package com.example.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建时间 2018年九月27日 星期四 19:25
 * 作者: zhangjunping
 * 消息发送(点对点模式）
 */
public class ActiveMQProducter {

    private static String userName = "admin";
    private static String passWord = "admin";
    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "queue-demo";

    public static void main(String[] args) throws JMSException {
//        创建JMS链接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, passWord,brokerURL);
//        创建链接
        Connection connection = connectionFactory.createConnection();
//        开启链接
        connection.start();
//        创建会话，并指定会话的事务（true）以及应答模式（自动确认模式）
//        如果是以事务形式签收的话，生产和消费必须Commit；手动签收一般 Session.CLIENT_ACKNOWLEDGE 消费者需要手动签收
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//        创建一个Queue（消息队列）
        Queue queue_demo = session.createQueue(queueName);
//        创建一个生产者
        MessageProducer producer = session.createProducer(queue_demo);
//        设置持久化方式（两种：一种持久化，一种非持久化）
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//        往消息队列中发送消息
        TextMessage textMessage = null;
        for (int i=0 ;i<10;i++) {
            textMessage = session.createTextMessage("点对点模式：hello！ i:" + i);
            producer.send(textMessage);
            session.commit();
            System.out.println("发送一个消息成功（i）：" + i);
        }
        connection.close();
    }
}
