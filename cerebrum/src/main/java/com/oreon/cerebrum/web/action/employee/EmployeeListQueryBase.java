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

	private Range<Date> dateOfBirthRange = new Range<Date>();

	public Range<Date> getDateOfBirthRange() {
		return dateOfBirthRange;
	}
	public void setDateOfBirth(Range<Date> dateOfBirthRange) {
		this.dateOfBirthRange = dateOfBirthRange;
	}

	private static final String[] RESTRICTIONS = {
			"employee.id = #{employeeList.employee.id}",

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

			"employee.dateCreated <= #{employeeList.dateCreatedRange.end}",
			"employee.dateCreated >= #{employeeList.dateCreatedRange.begin}",};

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
				+ (e.getAppUser() != null ? e.getAppUser().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getFacility() != null ? e.getFacility().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("AppUser" + ",");

		builder.append("Facility" + ",");

		builder.append("\r\n");
	}
}
