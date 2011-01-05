package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Project;

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

import org.wc.trackrite.issues.Project;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ProjectListQueryBase extends BaseQuery<Project, Long> {

	private static final String EJBQL = "select project from Project project";

	protected Project project = new Project();

	public Project getProject() {
		return project;
	}

	private org.wc.trackrite.domain.Employee employeeToSearch;

	public void setEmployeeToSearch(
			org.wc.trackrite.domain.Employee employeeToSearch) {
		this.employeeToSearch = employeeToSearch;
	}

	public org.wc.trackrite.domain.Employee getEmployeeToSearch() {
		return employeeToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void validate() {
		setQueryCacheable();
		super.validate();
	}

	private static final String[] RESTRICTIONS = {
			"project.id = #{projectList.project.id}",

			"lower(project.name) like concat(lower(#{projectList.project.name}),'%')",

			"lower(project.description) like concat(lower(#{projectList.project.description}),'%')",

			"#{projectList.employeeToSearch} in elements(project.employees)",

			"project.dateCreated <= #{projectList.dateCreatedRange.end}",
			"project.dateCreated >= #{projectList.dateCreatedRange.begin}",};

	@Observer("archivedProject")
	public void onArchive() {
		refresh();
	}

}
