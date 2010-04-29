package com.pge.propel.cc.action;

import com.pge.propel.cc.solution;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.solution;

public abstract class solutionListQueryBase extends EntityQuery<solution> {

	private static final String EJBQL = "select solution from solution solution";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private solution solution = new solution();

	private static final String[] RESTRICTIONS = {

			"lower(solution.projects) like concat(lower(#{solutionListQuery.solution.projects}),'%')",

			"lower(solution.company) like concat(lower(#{solutionListQuery.solution.company}),'%')",

			"solution.dateCreated <= #{solutionListQuery.dateCreatedRange.end}",
			"solution.dateCreated >= #{solutionListQuery.dateCreatedRange.begin}",};

	public solutionListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public solution getsolution() {
		return solution;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
