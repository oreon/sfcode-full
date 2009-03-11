package org.wcdemo.xstories.action;

import org.wcdemo.xstories.TeamMember;
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
@Name("teamMemberAction")
public class TeamMemberAction extends BaseAction<TeamMember>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private TeamMember teamMember;

	@DataModel
	private List<TeamMember> teamMemberList;

	@Factory("teamMemberList")
	public void findRecords() {
		teamMemberList = entityManager
				.createQuery(
						"select teamMember from TeamMember teamMember order by teamMember.id")
				.getResultList();
	}

	public TeamMember getEntity() {
		return teamMember;
	}

	@Override
	public void setEntity(TeamMember t) {
		this.teamMember = t;
	}

	@Override
	public void setEntityList(List<TeamMember> list) {
		this.teamMemberList = list;
	}

}
