package org.witchcraft.action.test;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.wc.trackrite.issues.Issue;



public class App
{
    public static String brokerURL = "tcp://localhost:61616";
 
    public static void main( String[] args ) throws Exception
    {
        // setup the connection to ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        Issue issue = new Issue();
        issue.setTitle("very msg friendly");
        Producer producer = new Producer(factory, "test", issue);
        producer.run();
        producer.close();
    }
}