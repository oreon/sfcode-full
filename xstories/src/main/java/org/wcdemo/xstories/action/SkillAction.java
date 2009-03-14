package org.wcdemo.xstories.action;

import org.wcdemo.xstories.Skill;
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
@Name("skillAction")
public class SkillAction extends BaseAction<Skill>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Skill skill;

	@DataModel
	private List<Skill> skillList;

	@Factory("skillList")
	public void findRecords() {
		skillList = entityManager.createQuery(
				"select skill from Skill skill order by skill.id desc")
				.getResultList();
	}

	public Skill getEntity() {
		return skill;
	}

	@Override
	public void setEntity(Skill t) {
		this.skill = t;
	}

	@Override
	public void setEntityList(List<Skill> list) {
		this.skillList = list;
	}

}
