package com.oreon.phonestore.web.action.domain;

import com.oreon.phonestore.domain.Employee;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.phonestore.domain.Employee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EmployeeListQueryBase extends BaseQuery<Employee, Long> {

	private static final String EJBQL = "select employee from Employee employee";

	protected Employee employee = new Employee();

	@In(create = true)
	EmployeeAction employeeAction;

	public EmployeeListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public Employee getInstance() {
		return getEmployee();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('employee', 'view')}")
	public List<Employee> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"employee.id = #{employeeList.employee.id}",

			"employee.archived = #{employeeList.employee.archived}",

			"lower(employee.firstName) like concat(lower(#{employeeList.employee.firstName}),'%')",

			"lower(employee.lastName) like concat(lower(#{employeeList.employee.lastName}),'%')",

			"lower(employee.contactDetails.phone) like concat(lower(#{employeeList.employee.contactDetails.phone}),'%')",

			"lower(employee.contactDetails.secondaryPhone) like concat(lower(#{employeeList.employee.contactDetails.secondaryPhone}),'%')",

			"lower(employee.contactDetails.city) like concat(lower(#{employeeList.employee.contactDetails.city}),'%')",

			"employee.department.id = #{employeeList.employee.department.id}",

			"lower(employee.employeeNumber) like concat(lower(#{employeeList.employee.employeeNumber}),'%')",

			"employee.employeeType = #{employeeList.employee.employeeType}",

			"employee.dateCreated <= #{employeeList.dateCreatedRange.end}",
			"employee.dateCreated >= #{employeeList.dateCreatedRange.begin}",};

	public LazyDataModel<Employee> getEmployeesByDepartment(
			final com.oreon.phonestore.domain.Department department) {

		EntityLazyDataModel<Employee, Long> employeeLazyDataModel = new EntityLazyDataModel<Employee, Long>(
				this) {

			@Override
			public List<Employee> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				employee.setDepartment(department);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return employeeLazyDataModel;

	}

	@Observer("archivedEmployee")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		employee.setFirstName(input);

		employee.setLastName(input);

		employee.getContactDetails().setPhone(input);

	}

	public void setDepartmentId(Long id) {
		if (employee.getDepartment() == null) {
			employee
					.setDepartment(new com.oreon.phonestore.domain.Department());
		}
		employee.getDepartment().setId(id);
	}

	public Long getDepartmentId() {
		return employee.getDepartment() == null ? null : employee
				.getDepartment().getId();
	}

	//@Restrict("#{s:hasPermission('employee', 'delete')}")
	public void archiveById(Long id) {
		employeeAction.archiveById(id);
		refresh();
	}

}
