package org.wcdemo.xstories.action;

import org.wcdemo.xstories.Project;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;

@Scope(ScopeType.CONVERSATION)
@Name("projectAction")
public class ProjectAction extends BaseAction<Project>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Project project;

	@DataModel
	private List<Project> projectList;

	@Factory("projectList")
	public void findRecords() {
		projectList = entityManager.createQuery(
				"select project from Project project order by project.id desc")
				.getResultList();
	}

	public Project getEntity() {
		return project;
	}

	@Override
	public void setEntity(Project t) {
		this.project = t;
	}

	@Override
	public void setEntityList(List<Project> list) {
		this.projectList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (project.getCoordinator() != null) {
			criteria = criteria.add(Restrictions.eq("coordinator.id", project
					.getCoordinator().getId()));
		}

		if (project.getTeamMember() != null) {
			criteria = criteria.add(Restrictions.eq("teamMember.id", project
					.getTeamMember().getId()));
		}

	}

}
