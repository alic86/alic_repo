package com.alic.third_part;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.junit.Test;

public class MyMessageProducer {
	public void sendMsg() throws NamingException, JMSException{
		ConnectionFactory connectionFactory;
		Connection connection;
		Session session;
		Destination destination;
		MessageProducer producer;
		Message message;
		boolean useTransaction = false;
		
		Context ctx = new InitialContext();
		connectionFactory = (ConnectionFactory)ctx.lookup(ActiveMQConnection.DEFAULT_USER);
		connection = connectionFactory.createConnection();
		session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue("TEST.QUEUE");
		producer = session.createProducer(destination);
		try{
			
			message = session.createObjectMessage(getMessageBean());
			
			producer.send(message);
		}catch(JMSException jmsEx){
			jmsEx.printStackTrace();
		}finally{
			producer.close();
			session.close();
			connection.close();
		}
	}
	
	public MyMessageBean getMessageBean(){
		MyMessageBean msgBean = new MyMessageBean();
		msgBean.setId(1L);
		msgBean.setAge(30);
		msgBean.setHeight(163F);
		msgBean.setName("苏星哲");
		return msgBean;
	}
	
	@Test
	public void msgTest() throws NamingException, JMSException{
		sendMsg();
	}
}
