package com.mycompany.wcmda;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.wcmda.ADLFrontEnd;

public class Client {
	private static final Logger logger = Logger.getLogger(Client.class);
	
	private ADLFrontEnd clientService;

	public ADLFrontEnd getClientService() {
		return clientService;
	}

	public void setClientService(ADLFrontEnd clientService) {
		this.clientService = clientService;
	}
	
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"classpath:/beanContext.xml");
		
		Client client = (Client) context.getBean("client");
		client.save();
		System.out.println("save OK");

	}
	
	public void save(){
		clientService.save(new String("test"));
	}
	
}
