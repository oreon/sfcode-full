package org.cerebrum.domain.patient.action;

import org.cerebrum.domain.patient.ImmunizationType;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

public class ImmunizationTypeActionBase extends BaseAction<ImmunizationType>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ImmunizationType immunizationType;

	@DataModel
	private List<ImmunizationType> immunizationTypeList;

	@Factory("immunizationTypeList")
	@Observer("archivedImmunizationType")
	public void findRecords() {
		search();
	}

	public ImmunizationType getEntity() {
		return immunizationType;
	}

	@Override
	public void setEntity(ImmunizationType t) {
		this.immunizationType = t;
	}

	@Override
	public void setEntityList(List<ImmunizationType> list) {
		this.immunizationTypeList = list;
	}

	public void updateAssociations() {

	}

	public List<ImmunizationType> getEntityList() {
		if (immunizationTypeList == null) {
			findRecords();
		}
		return immunizationTypeList;
	}

}
