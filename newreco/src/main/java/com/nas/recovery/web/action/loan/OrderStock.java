package com.nas.recovery.web.action.loan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.annotations.*;
import org.jboss.seam.bpm.TaskInstance;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jbpm.taskmgmt.def.Task;

@Name("orderStock")
// @Scope(ScopeType.CONVERSATION)
public class OrderStock {

	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	Long processQuantity;
	
//	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
//	WorkItem workItem = new WorkItem();

	private int quantity;
	
	
	@RequestParameter
	String transName;

	public int getQuantity() {
		return quantity;
	}
/*
	public WorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}
*/
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Transactional
	public void makeDecision(){
		//FacesContext.getCurrentInstance()
		System.out.println("going to execute " + transName);
		try {
			Object componet = Component.getInstance("orderStock");
			Method method = componet.getClass().getMethod(transName);
			method.invoke(componet, new Object[]{});
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

	@CreateProcess(definition = "simple")
	public void startProcess() {
		//workItem.setQty(12);
		processQuantity = new Long(getQuantity());
		//workItem.setQty(new Integer(getQuantity()));
	}

	@StartTask
	@EndTask(transition = "next")
	public void done() {

		// TaskInstance task = new TaskInstance();
		// task.getTaskInstance().ge
		// task.getActorIdExpression()
		// task.getName()
		// task.getStartState().getName()
	}

	@StartTask
	@EndTask(transition = "cancel", beforeRedirect = true)
	public void cancel() {
		System.out.println("cancelling the task");
	}

}
