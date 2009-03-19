package org.cerebrum.domain.drug.action;

import org.cerebrum.domain.drug.Drug;
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
@Name("drugAction")
public class DrugAction extends BaseAction<Drug>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Drug drug;

	@DataModel
	private List<Drug> drugList;

	@Factory("drugList")
	public void findRecords() {
		drugList = entityManager.createQuery(
				"select drug from Drug drug order by drug.id desc")
				.getResultList();
	}

	public Drug getEntity() {
		return drug;
	}

	@Override
	public void setEntity(Drug t) {
		this.drug = t;
	}

	@Override
	public void setEntityList(List<Drug> list) {
		this.drugList = list;
	}

}
