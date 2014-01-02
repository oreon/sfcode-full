package com.oreon.phonestore.web.action.domain;

import com.oreon.phonestore.domain.Employee;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.seam.action.BaseQuery;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

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

	public List<Employee> getEmployeesByDepartment(
			com.oreon.phonestore.domain.Department department) {
		employee.setDepartment(department);
		return getResultList();
	}

	@Observer("archivedEmployee")
	public void onArchive() {
		refresh();
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

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Employee e) {

		builder.append("\""
				+ (e.getDepartment() != null ? e.getDepartment()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getEmployeeNumber() != null ? e.getEmployeeNumber()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getEmployeeType() != null ? e.getEmployeeType() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Department" + ",");

		builder.append("EmployeeNumber" + ",");

		builder.append("EmployeeType" + ",");

		builder.append("\r\n");
	}
}
