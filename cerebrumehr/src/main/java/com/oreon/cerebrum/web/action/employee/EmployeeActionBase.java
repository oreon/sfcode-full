package com.oreon.cerebrum.web.action.employee;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.employee.Employee;

public abstract class EmployeeActionBase
		extends
			com.oreon.cerebrum.web.action.patient.PersonAction<Employee>
		implements
			java.io.Serializable {

	

	@In(create = true, value = "appUserAction")
	com.oreon.cerebrum.web.action.users.AppUserAction appUserAction;

	@In(create = true, value = "facilityAction")
	com.oreon.cerebrum.web.action.facility.FacilityAction facilityAction;

	@In(create = true, value = "unusualOccurenceAction")
	com.oreon.cerebrum.web.action.unusualoccurences.UnusualOccurenceAction unusualOccurencesAction;

	public void setEmployeeId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		instance = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setEmployeeIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
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

	public void setFacilityId(Long id) {

		if (id != null && id > 0)
			getInstance().setFacility(facilityAction.loadFromId(id));

	}

	public Long getFacilityId() {
		if (getInstance().getFacility() != null)
			return getInstance().getFacility().getId();
		return 0L;
	}

	public Long getEmployeeId() {
		return (Long) getId();
	}

	public Employee getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Employee t) {
		this.instance =t;
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

		com.oreon.cerebrum.users.AppUser appUser = appUserAction
				.getDefinedInstance();
		if (appUser != null && isNew()) {
			getInstance().setAppUser(appUser);
		}

		com.oreon.cerebrum.facility.Facility facility = facilityAction
				.getDefinedInstance();
		if (facility != null && isNew()) {
			getInstance().setFacility(facility);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Employee getDefinedInstance() {
		return (Employee) (isIdDefined() ? getInstance() : null);
	}

	public void setEmployee(Employee t) {
		this.instance =t;
		if (instance != null)
			setEmployeeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	public com.oreon.cerebrum.employee.Employee findByUnqEmployeeNumber(
			String employeeNumber) {
		return executeSingleResultNamedQuery(
				"employee.findByUnqEmployeeNumber", employeeNumber);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getAppUser() != null) {
			criteria = criteria.add(Restrictions.eq("appUser.id", instance
					.getAppUser().getId()));
		}

		if (instance.getFacility() != null) {
			criteria = criteria.add(Restrictions.eq("facility.id", instance
					.getFacility().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (instance.getAppUser() != null) {
			appUserAction.setInstance(getInstance().getAppUser());
			appUserAction.loadAssociations();
		}

		if (instance.getFacility() != null) {
			facilityAction.setInstance(getInstance().getFacility());
			facilityAction.loadAssociations();
		}

	}

	

	public String viewEmployee() {
		load(currentEntityId);
		return "viewEmployee";
	}

}
