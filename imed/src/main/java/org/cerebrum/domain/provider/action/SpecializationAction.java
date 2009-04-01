package org.cerebrum.domain.provider.action;

import org.cerebrum.domain.provider.Specialization;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
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
import org.jboss.seam.annotations.Observer;

@Scope(ScopeType.CONVERSATION)
@Name("specializationAction")
public class SpecializationAction extends BaseAction<Specialization>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Specialization specialization;

	@DataModel
	private List<Specialization> specializationList;

	@Factory("specializationList")
	@Observer("archivedSpecialization")
	public void findRecords() {
		search();
	}

	public Specialization getEntity() {
		return specialization;
	}

	@Override
	public void setEntity(Specialization t) {
		this.specialization = t;
	}

	@Override
	public void setEntityList(List<Specialization> list) {
		this.specializationList = list;
	}

	public void updateAssociations() {

	}

	public List<Specialization> getEntityList() {
		if (specializationList == null) {
			findRecords();
		}
		return specializationList;
	}

}
