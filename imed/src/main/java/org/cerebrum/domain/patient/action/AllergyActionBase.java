package org.cerebrum.domain.patient.action;

import org.cerebrum.domain.patient.Allergy;
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

public class AllergyActionBase extends BaseAction<Allergy>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Allergy allergy;

	@DataModel
	private List<Allergy> allergyList;

	@Factory("allergyList")
	@Observer("archivedAllergy")
	public void findRecords() {
		search();
	}

	public Allergy getEntity() {
		return allergy;
	}

	@Override
	public void setEntity(Allergy t) {
		this.allergy = t;
	}

	@Override
	public void setEntityList(List<Allergy> list) {
		this.allergyList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (allergy.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", allergy
					.getPatient().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Allergy> getEntityList() {
		if (allergyList == null) {
			findRecords();
		}
		return allergyList;
	}

}
