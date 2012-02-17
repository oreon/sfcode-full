package com.pcas.datapkg.web.action.domain;

import com.pcas.datapkg.domain.Employee;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import com.pcas.datapkg.domain.Employee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EmployeeListQueryBase extends BaseQuery<Employee, Long> {

	private static final String EJBQL = "select employee from Employee employee";

	protected Employee employee = new Employee();

	public Employee getEmployee() {
		return employee;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<BigDecimal> salaryRange = new Range<BigDecimal>();

	public Range<BigDecimal> getSalaryRange() {
		return salaryRange;
	}
	public void setSalary(Range<BigDecimal> salaryRange) {
		this.salaryRange = salaryRange;
	}

	private Range<Date> appUser_lastLoginRange = new Range<Date>();

	public Range<Date> getAppUser_lastLoginRange() {
		return appUser_lastLoginRange;
	}
	public void setAppUser_lastLogin(Range<Date> appUser_lastLoginRange) {
		this.appUser_lastLoginRange = appUser_lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"employee.id = #{employeeList.employee.id}",

			"lower(employee.firstName) like concat(lower(#{employeeList.employee.firstName}),'%')",

			"lower(employee.lastName) like concat(lower(#{employeeList.employee.lastName}),'%')",

			"lower(employee.contactDetails.phone) like concat(lower(#{employeeList.employee.contactDetails.phone}),'%')",

			"lower(employee.contactDetails.secondaryPhone) like concat(lower(#{employeeList.employee.contactDetails.secondaryPhone}),'%')",

			"lower(employee.contactDetails.city) like concat(lower(#{employeeList.employee.contactDetails.city}),'%')",

			"employee.department.id = #{employeeList.employee.department.id}",

			"lower(employee.employeeNumber) like concat(lower(#{employeeList.employee.employeeNumber}),'%')",

			"employee.employeeType = #{employeeList.employee.employeeType}",

			"employee.salary >= #{employeeList.salaryRange.begin}",
			"employee.salary <= #{employeeList.salaryRange.end}",

			"employee.customer.id = #{employeeList.employee.customer.id}",

			"lower(employee.appUser.userName) like concat(lower(#{employeeList.employee.appUser.userName}),'%')",

			"employee.appUser.enabled = #{employeeList.employee.appUser.enabled}",

			"lower(employee.appUser.email) like concat(lower(#{employeeList.employee.appUser.email}),'%')",

			"employee.appUser.lastLogin >= #{employeeList.appUser_lastLoginRange.begin}",
			"employee.appUser.lastLogin <= #{employeeList.appUser_lastLoginRange.end}",

			"employee.dateCreated <= #{employeeList.dateCreatedRange.end}",
			"employee.dateCreated >= #{employeeList.dateCreatedRange.begin}",};

	public List<Employee> getEmployeesByDepartment(
			com.pcas.datapkg.domain.Department department) {
		//setMaxResults(10000);
		employee.setDepartment(department);
		return getResultList();
	}

	public List<Employee> getEmployeesByCustomer(
			com.pcas.datapkg.domain.inventory.Customer customer) {
		//setMaxResults(10000);
		employee.setCustomer(customer);
		return getResultList();
	}

	@Observer("archivedEmployee")
	public void onArchive() {
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

		builder.append("\"" + (e.getSalary() != null ? e.getSalary() : "")
				+ "\",");

		builder.append("\""
				+ (e.getCustomer() != null ? e.getCustomer().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAppUser() != null ? e.getAppUser().getDisplayName()
						.replace(",", "") : "") + "\",");

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

		builder.append("Salary" + ",");

		builder.append("Customer" + ",");

		builder.append("AppUser" + ",");

		builder.append("\r\n");
	}
}
