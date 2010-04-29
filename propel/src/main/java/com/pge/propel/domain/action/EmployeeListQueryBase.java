package com.pge.propel.domain.action;

import com.pge.propel.domain.Employee;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.domain.Employee;

public abstract class EmployeeListQueryBase extends EntityQuery<Employee> {

	private static final String EJBQL = "select employee from Employee employee";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private Employee employee = new Employee();

	private static final String[] RESTRICTIONS = {

			"lower(employee.department) like concat(lower(#{employeeListQuery.employee.department}),'%')",

			"employee.dateCreated <= #{employeeListQuery.dateCreatedRange.end}",
			"employee.dateCreated >= #{employeeListQuery.dateCreatedRange.begin}",};

	public EmployeeListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Employee getEmployee() {
		return employee;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
