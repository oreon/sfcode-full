package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Employee;

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

import org.jboss.seam.annotations.security.Restrict;

import com.oreon.cerebrum.employee.Employee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EmployeeListQueryBase extends BaseQuery<Employee, Long> {

	private static final String EJBQL = "select employee from Employee employee";

	protected Employee employee = new com.oreon.cerebrum.employee.Physician();

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

	private Range<Date> dateOfBirthRange = new Range<Date>();

	public Range<Date> getDateOfBirthRange() {
		return dateOfBirthRange;
	}
	public void setDateOfBirth(Range<Date> dateOfBirthRange) {
		this.dateOfBirthRange = dateOfBirthRange;
	}

	private static final String[] RESTRICTIONS = {
			"employee.id = #{employeeList.employee.id}",

			"employee.archived = #{employeeList.employee.archived}",

			"lower(employee.firstName) like concat(lower(#{employeeList.employee.firstName}),'%')",

			"lower(employee.lastName) like concat(lower(#{employeeList.employee.lastName}),'%')",

			"employee.dateOfBirth >= #{employeeList.dateOfBirthRange.begin}",
			"employee.dateOfBirth <= #{employeeList.dateOfBirthRange.end}",

			"employee.gender = #{employeeList.employee.gender}",

			"lower(employee.contactDetails.primaryPhone) like concat(lower(#{employeeList.employee.contactDetails.primaryPhone}),'%')",

			"lower(employee.contactDetails.secondaryPhone) like concat(lower(#{employeeList.employee.contactDetails.secondaryPhone}),'%')",

			"lower(employee.contactDetails.email) like concat(lower(#{employeeList.employee.contactDetails.email}),'%')",

			"employee.title = #{employeeList.employee.title}",

			"lower(employee.appUser.userName) like concat(lower(#{employeeList.employee.appUser.userName}),'%')",

			"employee.appUser.enabled = #{employeeList.employee.appUser.enabled}",

			"employee.facility.id = #{employeeList.employee.facility.id}",

			"employee.department.id = #{employeeList.employee.department.id}",

			"employee.dateCreated <= #{employeeList.dateCreatedRange.end}",
			"employee.dateCreated >= #{employeeList.dateCreatedRange.begin}",};

	public List<Employee> getEmployeesByDepartment(
			com.oreon.cerebrum.employee.Department department) {
		employee.setDepartment(department);
		return getResultList();
	}

	@Observer("archivedEmployee")
	public void onArchive() {
		refresh();
	}

	public void setAppUserId(Long id) {
		if (employee.getAppUser() == null) {
			employee.setAppUser(new com.oreon.cerebrum.users.AppUser());
		}
		employee.getAppUser().setId(id);
	}

	public Long getAppUserId() {
		return employee.getAppUser() == null ? null : employee.getAppUser()
				.getId();
	}

	public void setFacilityId(Long id) {
		if (employee.getFacility() == null) {
			employee.setFacility(new com.oreon.cerebrum.facility.Facility());
		}
		employee.getFacility().setId(id);
	}

	public Long getFacilityId() {
		return employee.getFacility() == null ? null : employee.getFacility()
				.getId();
	}

	public void setDepartmentId(Long id) {
		if (employee.getDepartment() == null) {
			employee
					.setDepartment(new com.oreon.cerebrum.employee.Department());
		}
		employee.getDepartment().setId(id);
	}

	public Long getDepartmentId() {
		return employee.getDepartment() == null ? null : employee
				.getDepartment().getId();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Employee e) {

		builder.append("\""
				+ (e.getAppUser() != null ? e.getAppUser().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getFacility() != null ? e.getFacility().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDepartment() != null ? e.getDepartment()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("AppUser" + ",");

		builder.append("Facility" + ",");

		builder.append("Department" + ",");

		builder.append("\r\n");
	}
}
