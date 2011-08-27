package com.jonah.mentormatcher.web.action.domain;

import com.jonah.mentormatcher.domain.Employee;

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

import com.jonah.mentormatcher.domain.mentorship.Testimonial;

public abstract class EmployeeActionBase
		extends
			com.jonah.mentormatcher.web.action.domain.PersonAction<Employee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Employee employee;

	@In(create = true, value = "departmentAction")
	com.jonah.mentormatcher.web.action.domain.DepartmentAction departmentAction;

	@In(create = true, value = "appUserAction")
	com.jonah.mentormatcher.web.action.users.AppUserAction appUserAction;

	@In(create = true, value = "designationAction")
	com.jonah.mentormatcher.web.action.domain.DesignationAction designationAction;

	@In(create = true, value = "testimonialAction")
	com.jonah.mentormatcher.web.action.mentorship.TestimonialAction testimonialsAction;

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

	public void setAppUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setAppUser(appUserAction.loadFromId(id));

	}

	public Long getAppUserId() {
		if (getInstance().getAppUser() != null)
			return getInstance().getAppUser().getId();
		return 0L;
	}

	public void setDesignationId(Long id) {

		if (id != null && id > 0)
			getInstance().setDesignation(designationAction.loadFromId(id));

	}

	public Long getDesignationId() {
		if (getInstance().getDesignation() != null)
			return getInstance().getDesignation().getId();
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

		com.jonah.mentormatcher.domain.Department department = departmentAction
				.getDefinedInstance();
		if (department != null && isNew()) {
			getInstance().setDepartment(department);
		}

		com.jonah.mentormatcher.domain.users.AppUser appUser = appUserAction
				.getDefinedInstance();
		if (appUser != null && isNew()) {
			getInstance().setAppUser(appUser);
		}

		com.jonah.mentormatcher.domain.Designation designation = designationAction
				.getDefinedInstance();
		if (designation != null && isNew()) {
			getInstance().setDesignation(designation);
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

	public String downloadPicture(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getPicture());
		return "success";
	}

	public void pictureUploadListener(UploadEvent event) throws Exception {
		UploadItem uploadItem = event.getUploadItem();
		if (getInstance().getPicture() == null)
			getInstance().setPicture(new FileAttachment());
		getInstance().getPicture().setName(uploadItem.getFileName());
		getInstance().getPicture().setContentType(uploadItem.getContentType());
		getInstance().getPicture().setData(
				FileUtils.readFileToByteArray(uploadItem.getFile()));
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

		if (employee.getAppUser() != null) {
			criteria = criteria.add(Restrictions.eq("appUser.id", employee
					.getAppUser().getId()));
		}

		if (employee.getDesignation() != null) {
			criteria = criteria.add(Restrictions.eq("designation.id", employee
					.getDesignation().getId()));
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

		if (employee.getAppUser() != null) {
			appUserAction.setInstance(getInstance().getAppUser());
		}

		if (employee.getDesignation() != null) {
			designationAction.setInstance(getInstance().getDesignation());
		}

		initListTestimonials();

	}

	public void updateAssociations() {

		com.jonah.mentormatcher.domain.mentorship.Testimonial testimonials = (com.jonah.mentormatcher.domain.mentorship.Testimonial) org.jboss.seam.Component
				.getInstance("testimonial");
		testimonials.setEmployee(employee);
		events.raiseTransactionSuccessEvent("archivedTestimonial");

	}

	protected List<com.jonah.mentormatcher.domain.mentorship.Testimonial> listTestimonials = new ArrayList<com.jonah.mentormatcher.domain.mentorship.Testimonial>();

	void initListTestimonials() {

		if (listTestimonials.isEmpty())
			listTestimonials.addAll(getInstance().getTestimonials());

	}

	public List<com.jonah.mentormatcher.domain.mentorship.Testimonial> getListTestimonials() {

		prePopulateListTestimonials();
		return listTestimonials;
	}

	public void prePopulateListTestimonials() {
	}

	public void setListTestimonials(
			List<com.jonah.mentormatcher.domain.mentorship.Testimonial> listTestimonials) {
		this.listTestimonials = listTestimonials;
	}

	public void deleteTestimonials(int index) {
		listTestimonials.remove(index);
	}

	@Begin(join = true)
	public void addTestimonials() {
		initListTestimonials();
		Testimonial testimonials = new Testimonial();

		testimonials.setEmployee(getInstance());

		getListTestimonials().add(testimonials);
	}

	public void updateComposedAssociations() {

		if (listTestimonials != null) {
			getInstance().getTestimonials().clear();
			getInstance().getTestimonials().addAll(listTestimonials);
		}
	}

	public void clearLists() {
		listTestimonials.clear();

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
