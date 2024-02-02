package com.hivestreaming.assignment.sender;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.hivestreaming.assignment.Configuration;

import jakarta.jms.BytesMessage;
import jakarta.jms.Connection;
import jakarta.jms.DeliveryMode;
import jakarta.jms.Destination;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;

public class ActiveMQSender implements MessageSender {

	final private static String BROKER_URL = "tcp://localhost:61616";

	final private static String QUEUE_NAME = "TEST.FOO";

	// Create a ConnectionFactory
	private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

	public void sendMessage(byte[] bytes) throws Exception {
		Connection connection = null;
		Session session = null;

		try {
			// Create a Connection
			connection = this.connectionFactory.createConnection();
			connection.start();

			// Create a Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(QUEUE_NAME);

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Create a messages
			BytesMessage message = session.createBytesMessage();
			message.writeBytes(bytes);

			// Tell the producer to send the message
			System.out.println(String.format(" sent by %s from %s", Configuration.clientId,
					Thread.currentThread().getName()));
			producer.send(message);
		} finally {
			// Clean up
			session.close();
			connection.close();
		}
	}

}
