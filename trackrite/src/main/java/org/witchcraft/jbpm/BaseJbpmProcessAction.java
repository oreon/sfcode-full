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
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.bpm.ManagedJbpmContext;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jboss.seam.web.ServletContexts;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.Comment;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.joda.time.DateTime;

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

	protected TaskInstance task;

	protected String comment;

	@RequestParameter
	private Long taskId;

	@RequestParameter
	protected
	String transName;

	public String getTransName() {
		return transName;
	}

	@Transactional
	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getTaskId() {
		return taskId;
	}

	@Transactional
	public void setTaskId(long taskId) {
		this.taskId = taskId;
		loadTask();
	}

	@Transactional
	public void loadTask() {
		if (taskId > 0)
			task = ManagedJbpmContext.instance().getTaskInstance(taskId);
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
		// System.out.println("going to execute " + transName);
		String taskName = StringUtils.capitalize(task.getName());

		try {
			String name = task.getProcessInstance().getProcessDefinition()
					.getName(); // this.getClass().getAnnotation(Name.class).value();
			name = StringUtils.uncapitalize(name);
			name = name + PROCESS_ACTION_SUFFIX;
			//log.warn(" annotation name " + name);
			Object component = Component.getInstance(name);
			
		
			if(component == null){
				statusMessages.add("No such component " + name);
				return "failure";
			}

			loadInstance();
			String tn = ServletContexts.getInstance().getRequest().getParameter("transName");
			Method method = component.getClass().getMethod(transName + taskName);
			method.setAccessible(true);
			method.invoke(component, new Object[] {});

			if (task != null) {
				Comment comm = createComment();
				task.addComment(comm);
				task.end(transName);
				task.setEnd(new Date());
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

	protected void loadInstance() {
		// TODO Auto-generated method stub
		
	}

	private Comment createComment() {
		Comment comm = new Comment();
		comm.setMessage(comment);
		comm.setTime(new Date());
		comm.setActorId(identity.getCredentials().getUsername());
		return comm;
	}
	
	

	public List<Comment> getComments() {
		List<Comment> cmts = new ArrayList<Comment>();
		if (task != null) {
			Collection<TaskInstance> tasks = task.getProcessInstance()
					.getTaskMgmtInstance().getTaskInstances();

			for (TaskInstance taskInstance : tasks) {
				if(taskInstance.getComments() != null)
					cmts.addAll(taskInstance.getComments());
			}
		}
		return cmts;
	}

}
