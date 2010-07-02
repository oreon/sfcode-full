package org.witchcraft.action.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.testng.annotations.Test;



public class FriendRequestTest {
	public static void main(String args[])throws Exception {
		new FriendRequestTest().executeMine();
	}
	
	@Test
	public void executeMine() throws Exception{
		
		JbpmConfiguration config =JbpmConfiguration.parseInputStream(new FileInputStream(new File("src/main/resources/jbpm.cfg.xml") ));
		JbpmContext jbpmContext = config.createJbpmContext();
		//Hibern
		// Extract a process definition from the processdefinition.xml file.
		File file = new File("src/main/resources/processes/addFriendProcess/processdefinition.xml");
		//System.out.println( file.exists() );
		//ProcessDefinition.parseXmlInputStream(inputStream)
		//LeaveRequest lr = new LeaveRequest();
		
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlInputStream(new FileInputStream(file));
		HashMap map = new HashMap();
		
		map.put("to","singhjess@gmail.com"); 
		map.put("from", "jagdeepskohli@yahoo.com");
		map.put("goodsInStock",new Integer(50));
		//map.put("leaveRequest", lr);

		// Create an instance of the process definition.
		ProcessInstance instance = new ProcessInstance(processDefinition,map);
		System.out.println( JbpmContext.getCurrentJbpmContext().getTaskList().size() );
		displayStatus(instance);
		instance.signal();
		displayStatus(instance);
		
		executeTask(instance, "sendRequest");
		displayStatus(instance);
		
		executeTask(instance, "modify");
		displayStatus(instance);
		
		jbpmContext.close();
		
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
			transition = "moreDetails";
			if(from == "jagdeepskohli@yahoo.com")
				transition = "accept";
			
			//if(task.name=="")
			
				
			if (!taskInstance.hasEnded()){   
				taskInstance.end(transition);
				//System.out.println("Found Task "+taskInstance.getName());   
				System.out.println(taskInstance.getName() + " Task completed.");
				return;
				
			}
			
		}   


	}   

}


