package com.pcas.datapkg.web.action.domain;

import com.pcas.datapkg.domain.Employee;

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
			com.pcas.datapkg.web.action.domain.PersonAction<Employee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Employee employee;

	@In(create = true, value = "departmentAction")
	com.pcas.datapkg.web.action.domain.DepartmentAction departmentAction;

	@In(create = true, value = "customerAction")
	com.pcas.datapkg.web.action.inventory.CustomerAction customerAction;

	@In(create = true, value = "appUserAction")
	com.pcas.datapkg.web.action.users.AppUserAction appUserAction;

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

	public void setCustomerId(Long id) {

		if (id != null && id > 0)
			getInstance().setCustomer(customerAction.loadFromId(id));

	}

	public Long getCustomerId() {
		if (getInstance().getCustomer() != null)
			return getInstance().getCustomer().getId();
		return 0L;
	}

	public void setAppUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setAppUser(appUserAction.loadFromId(id));

	}

	public Long getAppUserId() {
		if (getInstance().getAppUser() != null)
			return getInstance().getAppUser().getId();
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
		Employee instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.domain.Department department = departmentAction
				.getDefinedInstance();
		if (department != null && isNew()) {
			getInstance().setDepartment(department);
		}

		com.pcas.datapkg.domain.inventory.Customer customer = customerAction
				.getDefinedInstance();
		if (customer != null && isNew()) {
			getInstance().setCustomer(customer);
		}

		com.pcas.datapkg.users.AppUser appUser = appUserAction
				.getDefinedInstance();
		if (appUser != null && isNew()) {
			getInstance().setAppUser(appUser);
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
		if (employee != null)
			setEmployeeId(t.getId());
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

		if (employee.getCustomer() != null) {
			criteria = criteria.add(Restrictions.eq("customer.id", employee
					.getCustomer().getId()));
		}

		if (employee.getAppUser() != null) {
			criteria = criteria.add(Restrictions.eq("appUser.id", employee
					.getAppUser().getId()));
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

		if (employee.getCustomer() != null) {
			customerAction.setInstance(getInstance().getCustomer());
		}

		if (employee.getAppUser() != null) {
			appUserAction.setInstance(getInstance().getAppUser());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	/** 
	 * []
	 */
	public String register() {

		return null;

	}

	/** 
	 * []
	 */
	public String login() {

		return null;

	}

	public com.pcas.datapkg.domain.Employee findByPhone(String phone) {

		return executeSingleResultNamedQuery("findByPhone", phone);

	}

	/** 
	 * []
	 */
	public String retrieveCredentials() {

		return null;

	}

	public Employee getCurrentLoggedInEmployee() {
		String query = "Select e from Employee e where e.appUser.userName = ?1";
		return (Employee) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String viewEmployee() {
		load(currentEntityId);
		return "viewEmployee";
	}

}
