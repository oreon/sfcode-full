package com.nas.recovery.web.action.domain;

import org.wc.trackrite.domain.Employee;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class EmployeeActionBase
		extends
			com.nas.recovery.web.action.domain.PersonAction<Employee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Employee employee;

	@In(create = true, value = "departmentAction")
	com.nas.recovery.web.action.domain.DepartmentAction departmentAction;

	@In(create = true, value = "issueList")
	com.nas.recovery.web.action.issues.IssueListQuery issueList;

	@DataModel
	private List<Employee> employeeRecordList;

	public void setEmployeeId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setDepartmentId(Long id) {
		if (id != null && id > 0)
			getInstance().setDepartment(departmentAction.loadFromId(id));
	}

	public Long getDepartmentId() {
		if (getInstance().getDepartment() != null)
			return getInstance().getDepartment().getId();
		return 0L;
	}

	public Long getEmployeeId() {
		return (Long) getId();
	}

	//@Factory("employeeRecordList")
	//@Observer("archivedEmployee")
	public void findRecords() {
		//search();
	}

	public Employee getEntity() {
		return employee;
	}

	@Override
	public void setEntity(Employee t) {
		this.employee = t;
		loadAssociations();
	}

	public Employee getEmployee() {
		return getInstance();
	}

	@Override
	protected Employee createInstance() {
		return new Employee();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		org.wc.trackrite.domain.Department department = departmentAction
				.getDefinedInstance();
		if (department != null) {
			getInstance().setDepartment(department);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Employee getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setEmployee(Employee t) {
		this.employee = t;
		loadAssociations();
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	public void setEntityList(List<Employee> list) {
		this.employeeRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (employee.getDepartment() != null) {
			criteria = criteria.add(Restrictions.eq("department.id", employee
					.getDepartment().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (employee.getDepartment() != null) {
			departmentAction.setInstance(getInstance().getDepartment());
		}

		try {

			issueList.getIssue().setDeveloper(getInstance());

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}

	}

	public void updateAssociations() {

		org.wc.trackrite.issues.Issue issue = (org.wc.trackrite.issues.Issue) org.jboss.seam.Component
				.getInstance("issue");
		issue.setDeveloper(employee);
		events.raiseTransactionSuccessEvent("archivedIssue");

	}

	public List<Employee> getEntityList() {
		if (employeeRecordList == null) {
			findRecords();
		}
		return employeeRecordList;
	}

}
