package com.nas.recovery.web.action.domain;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class ReadMail {

	public static void main(String args[]) throws Exception {

		String host = "pop.gmail.com";
		String username = "singhjess@gmail.com";
		Properties prop = new Properties();
		prop.setProperty("mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.pop3.socketFactory.fallback", "false");
		prop.setProperty("mail.pop3.port", "995");
		prop.setProperty("mail.pop3.socketFactory.port", "995");

		prop.put("mail.pop3.host", host);
		prop.put("mail.store.protocol", "pop3");
		Session session = Session.getDefaultInstance(prop);
		Store store = session.getStore();
		System.out.println("your ID is : " + username);
		System.out.println("Connecting...");
		store.connect(host, "singhjess@gmail.com", "XXXXX");
		System.out.println("Connected...");
		Folder inbox = store.getDefaultFolder().getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		// Get system properties
		Properties properties = System.getProperties();

		// Create a Folder object corresponding to the given name.
		Folder folder = store.getFolder("inbox");

		// Open the Folder.
		folder.open(Folder.READ_ONLY);

		Message[] message = folder.getMessages();

		// Display message.
		for (int i = 0; i < message.length; i++) {
			
			Date current = new Date();
			Date messageDate = message[i].getSentDate();
			if(messageDate.getMonth() < current.getMonth() || messageDate.getDay() < current.getDay())
				continue;
			
			if(!message[i].getSubject().startsWith("Daily Forex Analysis"))
				continue;

			System.out.println("------------ Message " + (i + 1)
					+ " ------------");

			System.out.println("SentDate : " + message[i].getSentDate());
			System.out.println("From : " + message[i].getFrom()[0]);
			System.out.println("Subject : " + message[i].getSubject());
			
			 Multipart multipart = (Multipart) message[i].getContent();
	            
	            for (int x = 0; x < multipart.getCount(); x++) {
	                BodyPart bodyPart = multipart.getBodyPart(x);

	                String disposition = bodyPart.getDisposition();

	                if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
	                    System.out.println("Mail have some attachment : ");

	                    DataHandler handler = bodyPart.getDataHandler();
	                    System.out.println("file name : " + handler.getName());
	                } else {
	                    System.out.println(bodyPart.getContent());
	                }
	            }
	            System.out.println();
	
			
			//if(i > 10 ) break;
		}

		folder.close(true);
		store.close();
	}
}