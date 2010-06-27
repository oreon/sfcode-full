package com.wc.sample;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class FriendRequestTest {
	public static void main(String args[])throws Exception {
		new FriendRequestTest().executeMine();
	}
	
	public void executeMine() throws Exception{
		// Extract a process definition from the processdefinition.xml file.
		File file = new File("src/main/resources/processes/addFriendProcess/processdefinition.xml");
		//System.out.println( file.exists() );
		//ProcessDefinition.parseXmlInputStream(inputStream)
		
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlInputStream(new FileInputStream(file));
		HashMap map = new HashMap();
		
		map.put("username","olivert"); 
		map.put("from", "rodney");
		map.put("goodsInStock",new Integer(50));
		//map.put("leaveRequest", lr);

		// Create an instance of the process definition.
		ProcessInstance instance = new ProcessInstance(processDefinition,map);

		displayStatus(instance);
		instance.signal();
		displayStatus(instance);
		
		executeTask(instance, "sendRequest");
		displayStatus(instance);
		
		executeTask(instance, "modify");
		displayStatus(instance);
		
	}
	
	public void execute() throws Exception {

		// Extract a process definition from the processdefinition.xml file.
		File file = new File("src/main/resources/processes/delivery.jpdl.xml");
		//System.out.println( file.exists() );
		//ProcessDefinition.parseXmlInputStream(inputStream)
		
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlInputStream(new FileInputStream(file));
		HashMap map = new HashMap();
		
		Order order = new Order(25, "hp pavillion" );

		map.put("goodsOrdered",new Integer(20)); 
		map.put("goodsInStock",new Integer(50));
		map.put("order", order);

		// Create an instance of the process definition.
		ProcessInstance instance = new ProcessInstance(processDefinition,map);

		displayStatus(instance);
		instance.signal();
		
		
		displayStatus(instance);
		
		executeTask(instance, "sendRequest");
		displayStatus(instance);
		
		executeTask(instance, "reject");
		displayStatus(instance);

	}

	private void displayStatus(ProcessInstance instance) {   
		String nodeName = instance.getRootToken().getNode().getName();   
		System.out.println("You are now in node: "+nodeName);   

	}   
	/* This is a dummy method which prints out the list of TaskInstances  and completes all the single tasks   
	 */  
	private void executeTask(ProcessInstance instance, String transition) {   

		Collection c = instance.getTaskMgmtInstance().getTaskInstances();   
		
		//System.out.println("there are tasks " + c.size());
		Iterator iter = c.iterator();   
		while (iter.hasNext()) {   

			TaskInstance taskInstance = (TaskInstance)iter.next();   
			taskInstance.getAvailableTransitions();
			
			System.out.println("assigned to " + taskInstance.getActorId());
			
			//taskInstance.end(transition);
			//taskInstance.getVariable(name)
			String from = (String) taskInstance.getVariable("from");
			
			//taskInstance..getTaskNode().g
			//if(taskInstance.getTask())
			transition = "addDetails";
			if(from == "ron")
				transition = "accept";
			
				
			if (!taskInstance.hasEnded()){   
				taskInstance.end(transition);
				//System.out.println("Found Task "+taskInstance.getName());   
				System.out.println(taskInstance.getName() + " Task completed.");
				return;
				
			}
			
		}   


	}   

}


