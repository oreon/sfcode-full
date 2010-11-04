package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Project;

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

import org.wc.trackrite.issues.Project;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ProjectListQueryBase extends BaseQuery<Project, Long> {

	//private static final String EJBQL = "select project from Project project";

	protected Project project = new Project();

	private static final String[] RESTRICTIONS = {
			"project.id = #{projectList.project.id}",

			"lower(project.name) like concat(lower(#{projectList.project.name}),'%')",

			"lower(project.description) like concat(lower(#{projectList.project.description}),'%')",

			"project.dateCreated <= #{projectList.dateCreatedRange.end}",
			"project.dateCreated >= #{projectList.dateCreatedRange.begin}",};

	public Project getProject() {
		return project;
	}

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedProject")
	public void onArchive() {
		refresh();
	}
}
