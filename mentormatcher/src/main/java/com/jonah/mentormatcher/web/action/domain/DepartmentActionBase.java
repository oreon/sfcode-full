package com.jonah.mentormatcher.web.action.domain;

import com.jonah.mentormatcher.domain.Department;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.jonah.mentormatcher.domain.Employee;

public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Department department;

	@In(create = true, value = "employeeAction")
	com.jonah.mentormatcher.web.action.domain.EmployeeAction employeesAction;

	@DataModel
	private List<Department> departmentRecordList;

	public void setDepartmentId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDepartmentIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getDepartmentId() {
		return (Long) getId();
	}

	public Department getEntity() {
		return department;
	}

	//@Override
	public void setEntity(Department t) {
		this.department = t;
		loadAssociations();
	}

	public Department getDepartment() {
		return (Department) getInstance();
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
		return (Department) (isIdDefined() ? getInstance() : null);
	}

	public void setDepartment(Department t) {
		this.department = t;
		if (department != null)
			setDepartmentId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	public com.jonah.mentormatcher.domain.Department findByUnqName(String name) {
		return executeSingleResultNamedQuery("department.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListEmployees();

	}

	public void updateAssociations() {

		com.jonah.mentormatcher.domain.Employee employees = (com.jonah.mentormatcher.domain.Employee) org.jboss.seam.Component
				.getInstance("employee");
		employees.setDepartment(department);
		events.raiseTransactionSuccessEvent("archivedEmployee");

	}

	protected List<com.jonah.mentormatcher.domain.Employee> listEmployees = new ArrayList<com.jonah.mentormatcher.domain.Employee>();

	void initListEmployees() {

		if (listEmployees.isEmpty())
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<com.jonah.mentormatcher.domain.Employee> getListEmployees() {

		prePopulateListEmployees();
		return listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void setListEmployees(
			List<com.jonah.mentormatcher.domain.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public void deleteEmployees(int index) {
		listEmployees.remove(index);
	}

	@Begin(join = true)
	public void addEmployees() {
		initListEmployees();
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

	public void clearLists() {
		listEmployees.clear();

	}

	public String viewDepartment() {
		load(currentEntityId);
		return "viewDepartment";
	}

}
