package com.wc.sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.annotations.*;
import org.jboss.seam.bpm.ProcessInstance;
import org.jboss.seam.bpm.TaskInstance;
import org.jboss.seam.ScopeType;
import org.jbpm.taskmgmt.def.Task;

@Name("leaveRequestAction")
// @Scope(ScopeType.CONVERSATION)
public class LeaveRequestAction {

	//@In(create=true)
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	int numDays;
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	String title;
	
	private LeaveRequest leaveRequest = new LeaveRequest();
	
	@RequestParameter
	String transName;
	
	//@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	//private LeaveRequest taskLeaveRequest;

	public LeaveRequest getLeaveRequest() {
		return leaveRequest;
	}

	public void setLeaveRequest(LeaveRequest leaveRequest) {
		this.leaveRequest = leaveRequest;
	}

	@CreateProcess(definition = "LeaveRequest")
	public void startProcess() {
		numDays = leaveRequest.getNumDays();
		System.out.println("setting title to " + title);
		title = leaveRequest.getTitle();
		//taskLeaveRequest = leaveRequest;
		//ProcessInstance.instance().get
		//org.jboss.seam.bpm.ProcessInstance
		//processQuantity = new Long(getQuantity());
	}
	
	
	public void makeDecision(){
		//FacesContext.getCurrentInstance()
		System.out.println("going to execute " + transName);
		try {
			Method method = this.getClass().getMethod(transName);
			method.invoke(this, null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@StartTask
	@EndTask(transition = "accept")
	public void approve() {
		
	}

	@StartTask
	@EndTask(transition = "reject")
	public void reject() {
		System.out.println("task xx has been rejected");
	}
	
	@StartTask
	@EndTask(transition = "modify")
	public void modify() {
		//TaskInstance task = new TaskInstance();
	}
	
	@StartTask
	@EndTask(transition = "sendRequest")
	public void send() {
		//TaskInstance task = new TaskInstance();
	}


}
