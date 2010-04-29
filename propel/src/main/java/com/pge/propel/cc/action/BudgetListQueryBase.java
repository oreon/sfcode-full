package com.pge.propel.cc.action;

import com.pge.propel.cc.Budget;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.Budget;

public abstract class BudgetListQueryBase extends EntityQuery<Budget> {

	private static final String EJBQL = "select budget from Budget budget";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private Budget budget = new Budget();

	private static final String[] RESTRICTIONS = {

			"lower(budget.lineItem) like concat(lower(#{budgetListQuery.budget.lineItem}),'%')",

			"lower(budget.project) like concat(lower(#{budgetListQuery.budget.project}),'%')",

			"lower(budget.name) like concat(lower(#{budgetListQuery.budget.name}),'%')",

			"budget.dateCreated <= #{budgetListQuery.dateCreatedRange.end}",
			"budget.dateCreated >= #{budgetListQuery.dateCreatedRange.begin}",};

	public BudgetListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Budget getBudget() {
		return budget;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
