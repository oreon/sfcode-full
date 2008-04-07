package com.mycomapny.employeemgr.web.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.mycomapny.employeemgr.domain.Employee;
import com.mycomapny.employeemgr.domain.Task;
import com.mycomapny.employeemgr.service.EmployeeService;

public class EmployeeBackingBean extends BaseBackingBean<Employee> {

	private Employee employee = new Employee();

	private EmployeeService employeeService;

	private List<Task> listTasks = new ArrayList<Task>();

	public static final int INITIAL_RECORDS = 5;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void set(Employee employee) {
		this.employee = employee;
	}

	public List<Task> getListTasks() {
		//if (listTasks.isEmpty()) {
		listTasks.clear();	
		loadTasks();
		//}
		return listTasks;
	}

	public void setListTasks(List<Task> listTasks) {
		this.listTasks = listTasks;
	}

	private void loadTasks() {
		if (employee != null) {
			listTasks.addAll(employee.getTask());
		}
		int sizeOfExistingElements = listTasks.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add
		// 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listTasks.add(new Task());
		}
	}

	@SuppressWarnings("unchecked")
	public BaseService<Employee> getBaseService() {
		return employeeService;
	}

	public Employee getEntity() {
		return getEmployee();
	}

	@Override
	public String search() {
		employee = new Employee(); 
		action = SEARCH;
		return "search";
	}

	/**
	 * Returns a success string upon selection of an entity in model - majority
	 * of work is done in the actionListener selectEntity
	 * 
	 * @return - "success" if everthing goes fine
	 * @see -
	 */
	public String select() {
		return "edit";
	}

	/**
	 * This action Listener Method is called when a row is clicked in the
	 * dataTable
	 * 
	 * @param event
	 *            contains the database id of the row being selected
	 */
	public void selectEntity(ActionEvent actionEvent) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		String idStr = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);

		employee = employeeService.load(id);
	}

	@Override
	public String update() {
		addTasksToEmployee();
		return super.update();
	}

	private void addTasksToEmployee() {
		employee.getTask().clear();
		List<Task> listValidTasks = new ArrayList<Task>();
		
		for (Task task : listTasks) {
			if (StringUtils.isNotEmpty(task.getTitle())){
				task.setEmployee(employee);
				listValidTasks.add(task);
			}
		}

		employee.getTask().addAll(listValidTasks);
	}

}
