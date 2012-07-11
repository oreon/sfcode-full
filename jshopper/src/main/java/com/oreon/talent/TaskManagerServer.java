package com.oreon.talent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.SystemEventListenerFactory;
import org.jbpm.task.Group;
import org.jbpm.task.User;
import org.jbpm.task.service.TaskService;
import org.jbpm.task.service.TaskServiceSession;
import org.jbpm.task.service.mina.MinaTaskServer;
import org.witchcraft.seam.action.EMFactory;

@ApplicationScoped
public class TaskManagerServer {

	static TaskService taskService;
	
	@PostConstruct
	public void startServer(){
		EntityManagerFactory emf = EMFactory.getEntityManagerFactory();
		if(taskService != null)
			return;
		
		taskService = new TaskService(emf, SystemEventListenerFactory.getSystemEventListener());
		//taskService.
		
		
		TaskServiceSession taskSession = taskService.createSession();

		// now register new users and groups

		taskSession.addUser(new User("john"));

		taskSession.addGroup(new Group("developers"));
		
		MinaTaskServer server = new MinaTaskServer( taskService );
		Thread thread = new Thread( server );
		thread.start();
	}
}
