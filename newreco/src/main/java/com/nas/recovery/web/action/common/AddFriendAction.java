package com.nas.recovery.web.action.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.annotations.*;
import org.jboss.seam.bpm.ProcessInstance;
import org.jboss.seam.security.Identity;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jbpm.JbpmContext;
import org.jbpm.taskmgmt.def.Task;


@Name("addFriend")
@Scope(ScopeType.CONVERSATION)
public class AddFriendAction {

	//@In(create=true)
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	int numDays;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String to;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String from;
	
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	FriendShipRequest friendshipRequest = new FriendShipRequest();
	
	
	@In JbpmContext jbpmContext;
	
	@In Identity identity;
	
	@RequestParameter
	String transName;


	private  TaskInstance task;
	
	//@RequestParameter
	private long taskInstanceId;
	
	public long getTaskInstanceId() {
		return taskInstanceId;
	}


	@Transactional
	public void setTaskInstanceId(long taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
		task = jbpmContext.getTaskInstance(taskInstanceId);
	}
	
	public TaskInstance getTask() {
		return task;
	}


	public void setTask(TaskInstance task) {
		this.task = task;
	}


	private String username;
	
	@Transactional
	public void makeDecision(){
		//FacesContext.getCurrentInstance()
		System.out.println("going to execute " + transName);
		try {
			Object componet = Component.getInstance("addFriend");
			Method method = componet.getClass().getMethod(transName);
			method.invoke(componet, new Object[]{});
			if(task != null){
				task.end(transName);
				task.setEnd(new Date());
			}
			//jbpmContext.
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}

	@CreateProcess(definition = "addFriendProcess")
	public void startProcess() {
		//numDays = leaveRequest.getNumDays();
		System.out.println("setting title to " + to);
		friendshipRequest.to = username;//leaveRequest.getTo();
		friendshipRequest.from =  identity.getCredentials().getUsername();
		//taskLeaveRequest = leaveRequest;
		//ProcessInstance.instance().get
		//org.jboss.seam.bpm.ProcessInstance
		//processQuantity = new Long(getQuantity());
	}
	
	
	public void selectTask(){
		
		//return "/manageAccount/viewTask.xhtml";
	}

	@StartTask
	@EndTask(transition = "accept")
	public void approve() {
		
	}

	@StartTask
	@EndTask(transition = "reject")
	public void reject() {
	}
	
	
	public void modifyBegin
	() {
		//TaskInstance task = new TaskInstance();
	}
	
	@StartTask
	@EndTask(transition = "moreDetails")
	public void moreDetails(){
		//task.e
	}
	
	@StartTask
	@EndTask(transition = "reviewDetails")
	public void reviewDetails(){
		//task.e
	}
	
	@StartTask
	@EndTask(transition = "sendRequest")
	public void send() {
		//jbpmContext.getP
		//TaskInstance task = new TaskInstance();
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUsername() {
		return username;
	}


}
