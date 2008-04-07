package com.mycomapny.employeemgr.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.mycomapny.employeemgr.domain.Task;
import com.mycomapny.employeemgr.service.TaskService;

public class TaskBackingBean extends BaseBackingBean<Task> {

	private Task task = new Task();

	private TaskService taskService;

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public Task getTask() {
		return task;
	}

	public void set(Task task) {
		this.task = task;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Task> getBaseService() {
		return taskService;
	}

	public Task getEntity() {
		return getTask();
	}

	public String search() {
		action = SEARCH;
		return "search";
	}

	/** Returns a success string upon selection of an entity in model - majority of work is done
	 * in the actionListener selectEntity
	 * @return - "success" if everthing goes fine
	 * @see - 
	 */
	public String select() {
		return "edit";
	}

	/** This action Listener Method is called when a row is clicked in the dataTable
	 *  
	 * @param event contains the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent actionEvent) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		String idStr = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);

		/*
		 UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		 // parse the value of the UIParameter component    	 
		 long id = Long.parseLong(component.getValue().toString());
		 */

		task = taskService.load(id);
	}

}
