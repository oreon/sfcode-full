package org.cerebrum.domain.demographics.action;

import org.cerebrum.domain.demographics.nameType;
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
@Name("nameTypeAction")
public class nameTypeAction extends BaseAction<nameType>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private nameType nameType;

	@DataModel
	private List<nameType> nameTypeList;

	@Factory("nameTypeList")
	public void findRecords() {
		nameTypeList = entityManager
				.createQuery(
						"select nameType from nameType nameType order by nameType.id desc")
				.getResultList();
	}

	public nameType getEntity() {
		return nameType;
	}

	@Override
	public void setEntity(nameType t) {
		this.nameType = t;
	}

	@Override
	public void setEntityList(List<nameType> list) {
		this.nameTypeList = list;
	}

	public List<nameType> getEntityList() {
		if (nameTypeList == null) {
			findRecords();
		}
		return nameTypeList;
	}

}
