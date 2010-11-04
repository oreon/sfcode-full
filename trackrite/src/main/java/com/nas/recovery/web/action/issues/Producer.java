package com.nas.recovery.web.action.issues;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.witchcraft.base.entity.BusinessEntity;
 
public class Producer
{
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private BusinessEntity payload;
 
    public Producer(ConnectionFactory factory, String queueName, BusinessEntity payload) throws JMSException
    {
        this.factory = factory;
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        producer = session.createProducer(destination);
        this.payload = payload;
    }
 
    public void run() throws JMSException
    {
        //for (int i = 0; i < 100; i++)
       // {
            System.out.println("Creating Message ");
            Message message = session.createObjectMessage(payload);
            producer.send(message);
       // }
    }
 
    public void close() throws JMSException
    {
        if (connection != null)
        {
            connection.close();
        }
    }
 
}