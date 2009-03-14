package org.wcdemo.xstories.action;

import org.wcdemo.xstories.MemberSkill;
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
@Name("memberSkillAction")
public class MemberSkillAction extends BaseAction<MemberSkill>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MemberSkill memberSkill;

	@DataModel
	private List<MemberSkill> memberSkillList;

	@Factory("memberSkillList")
	public void findRecords() {
		memberSkillList = entityManager
				.createQuery(
						"select memberSkill from MemberSkill memberSkill order by memberSkill.id desc")
				.getResultList();
	}

	public MemberSkill getEntity() {
		return memberSkill;
	}

	@Override
	public void setEntity(MemberSkill t) {
		this.memberSkill = t;
	}

	@Override
	public void setEntityList(List<MemberSkill> list) {
		this.memberSkillList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (memberSkill.getTeamMember() != null) {
			criteria = criteria.add(Restrictions.eq("teamMember.id",
					memberSkill.getTeamMember().getId()));
		}

		if (memberSkill.getSkill() != null) {
			criteria = criteria.add(Restrictions.eq("skill.id", memberSkill
					.getSkill().getId()));
		}

	}

}
