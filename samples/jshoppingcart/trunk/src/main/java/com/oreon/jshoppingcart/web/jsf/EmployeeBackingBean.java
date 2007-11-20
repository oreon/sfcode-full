package com.oreon.jshoppingcart.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.jshoppingcart.domain.Employee;
import com.oreon.jshoppingcart.service.EmployeeService;

public class EmployeeBackingBean extends BaseBackingBean<Employee> {

	private Employee employee = new Employee();

	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void set(Employee employee) {
		this.employee = employee;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Employee> getBaseService() {
		return employeeService;
	}

	public Employee getEntity() {
		return getEmployee();
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

		UIParameter component = (UIParameter) actionEvent.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());

		employee = employeeService.load(id);
	}

}
