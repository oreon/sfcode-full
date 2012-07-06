package com.oreon.talent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.SystemEventListenerFactory;
import org.jbpm.task.service.TaskService;
import org.jbpm.task.service.mina.MinaTaskServer;

@ApplicationScoped
public class TaskManagerServer {

	static TaskService taskService;
	
	@PostConstruct
	public void startServer(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");
		if(taskService != null)
			return;
		
		taskService = new TaskService(emf, SystemEventListenerFactory.getSystemEventListener());
		
		
		
		MinaTaskServer server = new MinaTaskServer( taskService );
		Thread thread = new Thread( server );
		thread.start();
	}
}
