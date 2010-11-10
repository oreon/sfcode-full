package com.nas.recovery.web.action.domain;

import org.wc.trackrite.domain.Department;

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
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.domain.Employee;

public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Department department;

	@In(create = true, value = "employeeList")
	com.nas.recovery.web.action.domain.EmployeeListQuery employeeList;

	@DataModel
	private List<Department> departmentRecordList;

	public void setDepartmentId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDepartmentIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getDepartmentId() {
		return (Long) getId();
	}

	//@Factory("departmentRecordList")
	//@Observer("archivedDepartment")
	public void findRecords() {
		//search();
	}

	public Department getEntity() {
		return department;
	}

	@Override
	public void setEntity(Department t) {
		this.department = t;
		loadAssociations();
	}

	public Department getDepartment() {
		return getInstance();
	}

	@Override
	protected Department createInstance() {
		return new Department();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Department getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setDepartment(Department t) {
		this.department = t;
		loadAssociations();
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	@Override
	public void setEntityList(List<Department> list) {
		this.departmentRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListEmployees();

		try {

			employeeList.getEmployee().setDepartment(getInstance());

		} catch (Exception e) {
			addErrorMessage("Error updating associaiton " + e.getMessage());
		}

	}

	public void updateAssociations() {

		org.wc.trackrite.domain.Employee employee = (org.wc.trackrite.domain.Employee) org.jboss.seam.Component
				.getInstance("employee");
		employee.setDepartment(department);
		events.raiseTransactionSuccessEvent("archivedEmployee");

	}

	protected List<org.wc.trackrite.domain.Employee> listEmployees;

	void initListEmployees() {
		listEmployees = new ArrayList<org.wc.trackrite.domain.Employee>();

		if (getInstance().getEmployees().isEmpty()) {

		} else
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<org.wc.trackrite.domain.Employee> getListEmployees() {
		if (listEmployees == null)
			initListEmployees();
		return listEmployees;
	}

	public void setListEmployees(
			List<org.wc.trackrite.domain.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public void deleteEmployees(int index) {
		listEmployees.remove(index);
	}

	@Begin(join = true)
	public void addEmployees() {
		Employee employees = new Employee();

		employees.setDepartment(getInstance());

		getListEmployees().add(employees);
	}

	public void updateComposedAssociations() {

		if (listEmployees != null) {
			getInstance().getEmployees().clear();
			getInstance().getEmployees().addAll(listEmployees);
		}
	}

	public List<Department> getEntityList() {
		if (departmentRecordList == null) {
			findRecords();
		}
		return departmentRecordList;
	}

}
