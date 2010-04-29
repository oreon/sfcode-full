package com.pge.propel.cc.action;

import com.pge.propel.cc.Company;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.Company;

public abstract class CompanyListQueryBase extends EntityQuery<Company> {

	private static final String EJBQL = "select company from Company company";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private Company company = new Company();

	private static final String[] RESTRICTIONS = {

			"lower(company.name) like concat(lower(#{companyListQuery.company.name}),'%')",

			"lower(company.solutions) like concat(lower(#{companyListQuery.company.solutions}),'%')",

			"lower(company.code) like concat(lower(#{companyListQuery.company.code}),'%')",

			"company.dateCreated <= #{companyListQuery.dateCreatedRange.end}",
			"company.dateCreated >= #{companyListQuery.dateCreatedRange.begin}",};

	public CompanyListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Company getCompany() {
		return company;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
