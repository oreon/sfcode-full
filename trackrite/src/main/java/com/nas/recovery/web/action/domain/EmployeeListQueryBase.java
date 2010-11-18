package com.nas.recovery.web.action.domain;

import org.wc.trackrite.domain.Employee;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.domain.Employee;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class EmployeeListQueryBase extends BaseQuery<Employee, Long> {

	//private static final String EJBQL = "select employee from Employee employee";

	protected Employee employee = new Employee();

	private Range<Date> user_lastLoginRange = new Range<Date>();
	public Range<Date> getUser_lastLoginRange() {
		return user_lastLoginRange;
	}
	public void setUser_lastLogin(Range<Date> user_lastLoginRange) {
		this.user_lastLoginRange = user_lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"employee.id = #{employeeList.employee.id}",

			"lower(employee.firstName) like concat(lower(#{employeeList.employee.firstName}),'%')",

			"lower(employee.lastName) like concat(lower(#{employeeList.employee.lastName}),'%')",

			"lower(employee.user.userName) like concat(lower(#{employeeList.employee.user.userName}),'%')",

			"employee.user.enabled = #{employeeList.employee.user.enabled}",

			"lower(employee.user.email) like concat(lower(#{employeeList.employee.user.email}),'%')",

			"employee.user.lastLogin >= #{employeeList.user_lastLoginRange.begin}",
			"employee.user.lastLogin <= #{employeeList.user_lastLoginRange.end}",

			"employee.department.id = #{employeeList.employee.department.id}",

			"lower(employee.employeeNumber) like concat(lower(#{employeeList.employee.employeeNumber}),'%')",

			"employee.employeeType = #{employeeList.employee.employeeType}",

			"lower(employee.home.primaryPhone) like concat(lower(#{employeeList.employee.home.primaryPhone}),'%')",

			"lower(employee.home.secondaryPhone) like concat(lower(#{employeeList.employee.home.secondaryPhone}),'%')",

			"lower(employee.home.email) like concat(lower(#{employeeList.employee.home.email}),'%')",

			"lower(employee.mailing.primaryPhone) like concat(lower(#{employeeList.employee.mailing.primaryPhone}),'%')",

			"lower(employee.mailing.secondaryPhone) like concat(lower(#{employeeList.employee.mailing.secondaryPhone}),'%')",

			"lower(employee.mailing.email) like concat(lower(#{employeeList.employee.mailing.email}),'%')",

			"employee.dateCreated <= #{employeeList.dateCreatedRange.end}",
			"employee.dateCreated >= #{employeeList.dateCreatedRange.begin}",};

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedEmployee")
	public void onArchive() {
		refresh();
	}
}