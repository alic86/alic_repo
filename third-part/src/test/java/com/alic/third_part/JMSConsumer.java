package com.alic.third_part;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQObjectMessage;
import org.junit.Test;

/** 
 * 消息消费者 
 * @author Administrator 
 * 
 */  
public class JMSConsumer {  
  
//    private static final String USERNAME=ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名  
//    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码  
//    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址  
//      
//    public void consumer() {  
//        ConnectionFactory connectionFactory; // 连接工厂  
//        Connection connection = null; // 连接  
//        Session session; // 会话 接受或者发送消息的线程  
//        Destination destination; // 消息的目的地  
//        MessageConsumer messageConsumer; // 消息的消费者  
//          
//        // 实例化连接工厂  
//        connectionFactory=new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);  
//                  
//        try {  
//            connection=connectionFactory.createConnection();  // 通过连接工厂获取连接  
//            connection.start(); // 启动连接  
//            session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session  
//            destination=session.createQueue("FirstQueue1");  // 创建连接的消息队列  
//            messageConsumer=session.createConsumer(destination); // 创建消息消费者  
//            while(true){  
////                TextMessage message=(TextMessage)messageConsumer.receive(100000);  
//            	ActiveMQObjectMessage message = (ActiveMQObjectMessage)messageConsumer.receive(10000);
//                if(message!=null){  
//                	MyMessageBean messageBean = (MyMessageBean)message.getObject();
//                	System.out.println(messageBean.getId());
//                	System.out.println(messageBean.getName());
//                	System.out.println(messageBean.getAge());
//                	System.out.println(messageBean.getHeight());
//                }else{  
//                    break;  
//                }  
//            }  
//        } catch (JMSException e) {  
//            e.printStackTrace();  
//        }   
//    }  
//    
//    @Test
//    public void consumerTest(){
//    	consumer();
//    }
}  
