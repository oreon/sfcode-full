package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.Project;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.Project;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ProjectListQueryBase extends BaseQuery<Project, Long> {

	//private static final String EJBQL = "select project from Project project";

	private Project project = new Project();

	private static final String[] RESTRICTIONS = {
			"project.id = #{projectList.project.id}",

			"lower(project.name) like concat(lower(#{projectList.project.name}),'%')",

			"project.description = #{projectList.project.description}",

			"project.architect = #{projectList.project.architect}",

			"project.manager = #{projectList.project.manager}",

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
}
