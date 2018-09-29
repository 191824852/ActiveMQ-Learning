package com.example.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建时间 2018年九月27日 星期四 19:42
 * 作者: zhangjunping
 * 消息消费
 */
public class ActiveMQReveiver {
    private static String userName = "admin";
    private static String passWord = "admin";
    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "queue-demo";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, passWord, brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.SESSION_TRANSACTED);
        Queue queue = session.createQueue(queueName);
//        创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
   /*     while (Boolean.TRUE){
            TextMessage receive = (TextMessage)consumer.receive();
            if (null != receive) {
                System.out.println("接收到一个消息：" + receive.getText());
                session.commit();
            }else{
                System.out.println("消息全部消费完毕！");
                break;
            }
        }*/
        consumer.setMessageListener(message -> {
            TextMessage msg = (TextMessage) message;
            try {
                System.out.println("msg = " + msg.getText());
                msg.acknowledge(); //代表已签收，可以理解为确认签收 （手动签收模式下）
//                session.commit(); // 事务方式commit
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
