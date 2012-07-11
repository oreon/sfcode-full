package org.witchcraft.action.test;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Persistence;

import org.drools.SystemEventListenerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.workitem.wsht.AsyncHornetQHTWorkItemHandler;
import org.jbpm.process.workitem.wsht.HornetQHTWorkItemHandler;
import org.jbpm.task.Content;
import org.jbpm.task.Group;
import org.jbpm.task.Task;
import org.jbpm.task.TaskService;
import org.jbpm.task.User;
import org.jbpm.task.identity.DefaultUserGroupCallbackImpl;
import org.jbpm.task.identity.UserGroupCallbackManager;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.jbpm.task.service.SyncTaskServiceWrapper;
import org.jbpm.task.service.hornetq.AsyncHornetQTaskClient;
import org.jbpm.task.service.hornetq.HornetQTaskServer;
import org.jbpm.task.utils.ContentMarshallerHelper;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Before;
import org.junit.Test;


public class HTHandlerTest extends JbpmJUnitTestCase {

	public HTHandlerTest() {
		super(true);
		setPersistence(true);
	}
	
	private HornetQTaskServer hornetQServer;

	
	@Test
	public void test() throws Exception {

		UserGroupCallbackManager.getInstance().setCallback(new DefaultUserGroupCallbackImpl("classpath:/roles.properties"));
		StatefulKnowledgeSession ksession = createKnowledgeSession("test.bpmn2");
		org.jbpm.task.service.TaskService taskService = new org.jbpm.task.service.TaskService(
		        Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa"), SystemEventListenerFactory.getSystemEventListener());
            
            Map vars = new HashMap();
            Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/LoadUsers.mvel"));     
            Map<String, User> users = ( Map<String, User> ) org.jbpm.task.service.TaskService.eval( reader, vars );
            
            reader = new InputStreamReader(this.getClass().getResourceAsStream("/LoadGroups.mvel"));
            Map<String, Group> groups = ( Map<String, Group> ) org.jbpm.task.service.TaskService.eval( reader, vars ); 
            
            taskService.addUsersAndGroups(users, groups);
		hornetQServer = new HornetQTaskServer(taskService, 5445);
        Thread t = new Thread(hornetQServer);
        t.start();
        Thread.sleep(2000);
		AsyncHornetQHTWorkItemHandler hornetQHTWorkItemHandler = new AsyncHornetQHTWorkItemHandler(ksession);
        ksession.getWorkItemManager().registerWorkItemHandler("Human Task", hornetQHTWorkItemHandler);
        // start a new process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("value", "value");
        ksession.startProcess("org.jenkinsci.plugins.jbpm.human", params);

        // we can reuse the client used by the Work Item Hander.
        TaskService taskClient = new SyncTaskServiceWrapper(new AsyncHornetQTaskClient("test"));

        taskClient.connect("127.0.0.1", 5445);
        
        Thread.sleep(1000);

        List<TaskSummary> tasks = taskClient.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        TaskSummary task1 = tasks.get(0);
        Thread.sleep(1000);
        
        taskClient.start(task1.getId(), "admin");
        Task task = taskClient.getTask(task1.getId());

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("data", "Accept");
        ContentData contentData = ContentMarshallerHelper.marshal(results,  null);

        taskClient.complete(task.getId(), "admin", contentData);

        Thread.sleep(2000);
        task = taskClient.getTask(task1.getId());
        System.out.println("Task status is " + task.getTaskData().getStatus());

        Thread.sleep(2000);
        taskClient.disconnect();
        hornetQHTWorkItemHandler.dispose();
	}

}
