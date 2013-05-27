package com.oreon.phonestore.web.action.domain;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Department;
import com.oreon.phonestore.domain.Employee;

public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Department department;

	@In(create = true, value = "employeeAction")
	com.oreon.phonestore.web.action.domain.EmployeeAction employeesAction;

	public void setDepartmentId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		department = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDepartmentIdForModalDlg(Long id) {
		setId(id);
		department = loadInstance();
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
	@Restrict("#{s:hasPermission('department', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('department', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Department createInstance() {
		Department instance = super.createInstance();

		return instance;
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

	public com.oreon.phonestore.domain.Department findByUnqName(String name) {
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

		com.oreon.phonestore.domain.Employee employees = (com.oreon.phonestore.domain.Employee) org.jboss.seam.Component
				.getInstance("employee");
		employees.setDepartment(department);
		events.raiseTransactionSuccessEvent("archivedEmployee");

	}

	protected List<com.oreon.phonestore.domain.Employee> listEmployees = new ArrayList<com.oreon.phonestore.domain.Employee>();

	void initListEmployees() {

		if (listEmployees.isEmpty())
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<com.oreon.phonestore.domain.Employee> getListEmployees() {

		prePopulateListEmployees();
		return listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void setListEmployees(
			List<com.oreon.phonestore.domain.Employee> listEmployees) {
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
