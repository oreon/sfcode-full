package org.wc.jbpm.myjbpm;



import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.Configuration;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		ProcessEngine processEngine = new Configuration().setResource(
				"my.jbpm.cfg.xml").buildProcessEngine();

		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ExecutionService executionService = processEngine.getExecutionService();
		repositoryService.createDeployment().addResourceFromClasspath(
				"hello_world.jpdl.xml").deploy();
		executionService.startProcessInstanceByKey("helloWorld");
	}

}
