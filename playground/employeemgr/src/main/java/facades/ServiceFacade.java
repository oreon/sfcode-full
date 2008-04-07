package facades;

import com.mycomapny.employeemgr.service.EmployeeService;

import com.mycomapny.employeemgr.service.TaskService;

public class ServiceFacade {

	private EmployeeService employeeService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	private TaskService taskService;

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}
