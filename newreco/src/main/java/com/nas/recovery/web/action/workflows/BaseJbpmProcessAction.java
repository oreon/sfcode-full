package com.nas.recovery.web.action.workflows;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.Comment;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.witchcraft.seam.action.BaseAction;

/**
 * This action will fucntion as base for all workflow related actions
 * 
 * @author jagdeep.singh
 * 
 */
public class BaseJbpmProcessAction {

	public static final String PROCESS_ACTION_SUFFIX = "ProcessAction";

	@In
	protected JbpmContext jbpmContext;

	@In
	Identity identity;
	
	@Logger
	protected Log log;
	
	@In
	protected StatusMessages statusMessages;

	@RequestParameter
	String transName;

	private TaskInstance task;
	
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// @RequestParameter
	private long taskInstanceId;

	public long getTaskInstanceId() {
		return taskInstanceId;
	}

	@Transactional
	public void setTaskInstanceId(long taskInstanceId) {
		
		this.taskInstanceId = taskInstanceId;
		if(taskInstanceId > 0 )
			task = jbpmContext.getTaskInstance(taskInstanceId);
	}

	public TaskInstance getTask() {
		return task;
	}

	public void setTask(TaskInstance task) {
		this.task = task;
	}

	@Transactional
	public void makeDecision() {
		// FacesContext.getCurrentInstance()
	//	System.out.println("going to execute " + transName);
		String taskName = StringUtils.capitalize(task.getName());
		
		try {
			String name =   task.getProcessInstance().getProcessDefinition().getName(); //this.getClass().getAnnotation(Name.class).value();
			log.warn(" annotation name "  + name );
			Object componet = Component.getInstance(name + PROCESS_ACTION_SUFFIX);
			Method method = componet.getClass().getMethod(transName + taskName);
			method.invoke(componet, new Object[] {});
			if (task != null) {
				task.end(transName);
				task.addComment(comment);
				task.setEnd(new Date());
				
				
			}
			// jbpmContext.
		} catch (Exception e) {
			statusMessages.add(e.getMessage());
			log.error("Error invoking workflow transition -> " + transName, e);
			e.printStackTrace();
		}
	}

}
