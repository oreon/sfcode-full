package com.oreon.talent.web.action.domain;

import com.oreon.talent.domain.Department;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DualListModel;

import org.witchcraft.utils.ViewUtils;
import javax.inject.Inject; import javax.ejb.Stateful;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.oreon.talent.domain.Employee;

public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	protected Predicate[] getSearchPredicates(Root<Department> root) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		/*
		String name = search.getName();
		if (name != null && !"".equals(name)) {
			predicatesList.add(builder.like(root.<String> get("name"),
					'%' + name + '%'));
		}
		
		int stock = search.getStock();
		if (stock != 0) {
			predicatesList.add(builder.equal(root.get("stock"), stock));
		}*/

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	@Override
	protected Class<Department> getEntityClass() {
		return Department.class;
	}

	public Department createInstance() {
		return new Department();
	}

	public Department getDepartment() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setDepartment(Department department) {
		this.entity = department;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListEmployees();

	}

	public void updateAssociations() {
	}

	protected List<com.oreon.talent.domain.Employee> listEmployees = new ArrayList<com.oreon.talent.domain.Employee>();

	void initListEmployees() {
		prePopulateListEmployees();
		listEmployees.addAll(getEntity().getEmployees());
	}

	public List<com.oreon.talent.domain.Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(
			List<com.oreon.talent.domain.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void deleteEmployees(int index) {
		listEmployees.remove(index);
	}

	public void addEmployees() {
		initListEmployees();
		Employee employees = new Employee();

		employees.setDepartment(getEntity());

		getListEmployees().add(employees);
	}

	public void updateComposedAssociations() {

		if (listEmployees != null) {
			getEntity().getEmployees().clear();
			getEntity().getEmployees().addAll(listEmployees);
		}
	}

	public void clearLists() {
		listEmployees.clear();

	}

	public com.oreon.talent.domain.Department findByUnqName(String name) {
		return executeSingleResultNamedQuery("department.findByUnqName", name);
	}

}
