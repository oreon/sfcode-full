package org.witchcraft.jbpm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.bpm.ManagedJbpmContext;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jboss.seam.web.ServletContexts;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.Comment;
import org.jbpm.taskmgmt.exe.TaskInstance;

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
	protected Identity identity;
	
	@Logger
	protected Log log;
	
	@In
	protected StatusMessages statusMessages;

	//@RequestParameter
	String transName;

	public String getTransName() {
		return transName;
	}

	@Transactional
	public void setTransName(String transName) {
		this.transName = transName;
	}

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
			task = ManagedJbpmContext.instance().getTaskInstance(taskInstanceId);
	}

	public TaskInstance getTask() {
		return task;
	}

	public void setTask(TaskInstance task) {
		this.task = task;
	}

	@Transactional
	public String makeDecision() {
		// FacesContext.getCurrentInstance()
	//	System.out.println("going to execute " + transName);
		String taskName = StringUtils.capitalize(task.getName());
		
		try {
			String name =   task.getProcessInstance().getProcessDefinition().getName(); //this.getClass().getAnnotation(Name.class).value();
			log.warn(" annotation name "  + name );
			Object componet = Component.getInstance(name + PROCESS_ACTION_SUFFIX);
			
			String tn = ServletContexts.getInstance().getRequest().getParameter("transName");
			Method method = componet.getClass().getMethod(transName + taskName);
			method.invoke(componet, new Object[] {});
			
			if (task != null) {
				task.addComment(comment);
				task.end(transName);
				task.setEnd(new Date());
				 
				//Comment cmt = task.getComments().get(0);
				//cmt.getActorId() + cmt.get
			}
			
			return "next";
			// jbpmContext.
		} catch (Exception e) {
			statusMessages.add(e.getMessage());
			log.error("Error invoking workflow transition -> " + transName, e);
			e.printStackTrace();
		}
		
		return "failed";
	}
	
	public List<Comment> getComments(){
		Collection<TaskInstance> tasks = task.getProcessInstance().getTaskMgmtInstance().getTaskInstances();
		List<Comment> cmts = new ArrayList<Comment>();
		for (TaskInstance taskInstance : tasks) {
			cmts.addAll(taskInstance.getComments());
		}
		return cmts;
	}

}
