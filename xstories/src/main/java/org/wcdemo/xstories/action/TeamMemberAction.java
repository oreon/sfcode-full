package org.wcdemo.xstories.action;

import org.wcdemo.xstories.TeamMember;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
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

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

import org.wcdemo.xstories.MemberSkill;

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
	
	private boolean modalRendered;

	@Factory("teamMemberList")
	public void findRecords() {
		teamMemberList = entityManager
				.createQuery(
						"select teamMember from TeamMember teamMember order by teamMember.id desc")
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

	private List<MemberSkill> listSkills;

	void initListSkills() {
		listSkills = new ArrayList<MemberSkill>();
		if (teamMember.getSkills().isEmpty()) {

			addSkills();

		} else
			listSkills.addAll(teamMember.getSkills());
	}

	public List<MemberSkill> getListSkills() {
		if (listSkills == null) {
			initListSkills();
		}
		return listSkills;
	}

	public void setListSkills(List<MemberSkill> listSkills) {
		this.listSkills = listSkills;
	}

	public void deleteSkills(MemberSkill skills) {
		listSkills.remove(skills);
	}

	@Begin(join = true)
	public void addSkills() {
		MemberSkill skills = new MemberSkill();
		skills.setTeamMember(teamMember);
		listSkills.add(skills);
	}

	public void updateComposedAssociations() {

		teamMember.getSkills().clear();
		teamMember.getSkills().addAll(listSkills);

	}
	
	public void toggleModal(TeamMember teamMember) {
		setEntity(entityManager.merge(teamMember));
        modalRendered = true;
    }
	
	public void archiveAndClose() {
		archive(getEntity());
        modalRendered = false;
    }
	
	public void closeModal() {
        modalRendered = false;
    }

	public boolean isModalRendered() {
		return modalRendered;
	}

	public void setModalRendered(boolean modalRendered) {
		this.modalRendered = modalRendered;
	}
	
	


}
