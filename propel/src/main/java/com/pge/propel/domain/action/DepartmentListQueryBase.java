package com.pge.propel.domain.action;

import com.pge.propel.domain.Department;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.domain.Department;

public abstract class DepartmentListQueryBase extends EntityQuery<Department> {

	private static final String EJBQL = "select department from Department department";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private Department department = new Department();

	private static final String[] RESTRICTIONS = {

			"lower(department.employee) like concat(lower(#{departmentListQuery.department.employee}),'%')",

			"lower(department.name) like concat(lower(#{departmentListQuery.department.name}),'%')",

			"department.dateCreated <= #{departmentListQuery.dateCreatedRange.end}",
			"department.dateCreated >= #{departmentListQuery.dateCreatedRange.begin}",};

	public DepartmentListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Department getDepartment() {
		return department;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
