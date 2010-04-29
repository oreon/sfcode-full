package com.pge.propel.cc.action;

import com.pge.propel.cc.Project;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.Project;

public abstract class ProjectListQueryBase extends EntityQuery<Project> {

	private static final String EJBQL = "select project from Project project";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private Project project = new Project();

	private static final String[] RESTRICTIONS = {

			"lower(project.solution) like concat(lower(#{projectListQuery.project.solution}),'%')",

			"lower(project.components) like concat(lower(#{projectListQuery.project.components}),'%')",

			"project.dateCreated <= #{projectListQuery.dateCreatedRange.end}",
			"project.dateCreated >= #{projectListQuery.dateCreatedRange.begin}",};

	public ProjectListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Project getProject() {
		return project;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
