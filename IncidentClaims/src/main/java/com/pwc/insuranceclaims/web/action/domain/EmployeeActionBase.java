package com.pwc.insuranceclaims.web.action.domain;

import com.pwc.insuranceclaims.domain.Employee;

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

public abstract class EmployeeActionBase
		extends
			com.pwc.insuranceclaims.web.action.domain.PersonAction<Employee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Employee employee;

	@In(create = true, value = "departmentAction")
	com.pwc.insuranceclaims.web.action.domain.DepartmentAction departmentAction;

	@In(create = true, value = "userAction")
	com.pwc.insuranceclaims.web.action.users.UserAction userAction;

	@DataModel
	private List<Employee> employeeRecordList;

	public void setEmployeeId(Long id) {
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
	public void setEmployeeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
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

	public void setUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setUser(userAction.loadFromId(id));

	}

	public Long getUserId() {
		if (getInstance().getUser() != null)
			return getInstance().getUser().getId();
		return 0L;
	}

	public Long getEmployeeId() {
		return (Long) getId();
	}

	public Employee getEntity() {
		return employee;
	}

	//@Override
	public void setEntity(Employee t) {
		this.employee = t;
		loadAssociations();
	}

	public Employee getEmployee() {
		return (Employee) getInstance();
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

		com.pwc.insuranceclaims.domain.Department department = departmentAction
				.getDefinedInstance();
		if (department != null && isNew()) {
			getInstance().setDepartment(department);
		}

		com.pwc.insuranceclaims.users.User user = userAction
				.getDefinedInstance();
		if (user != null && isNew()) {
			getInstance().setUser(user);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Employee getDefinedInstance() {
		return (Employee) (isIdDefined() ? getInstance() : null);
	}

	public void setEmployee(Employee t) {
		this.employee = t;
		loadAssociations();
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (employee.getDepartment() != null) {
			criteria = criteria.add(Restrictions.eq("department.id", employee
					.getDepartment().getId()));
		}

		if (employee.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", employee
					.getUser().getId()));
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

		if (employee.getUser() != null) {
			userAction.setInstance(getInstance().getUser());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String register() {

		return null;

	}

	public String login() {

		return null;

	}

	public com.pwc.insuranceclaims.domain.Employee findByPhone(String phone) {

		return executeSingleResultNamedQuery("findByPhone", phone);

	}

	public String retrieveCredentials() {

		return null;

	}

	public Employee getCurrentLoggedInEmployee() {
		String query = "Select e from Employee e where e.user.userName = ?1";
		return (Employee) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

}
