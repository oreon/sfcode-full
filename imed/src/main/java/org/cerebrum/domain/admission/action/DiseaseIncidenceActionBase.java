package org.cerebrum.domain.admission.action;

import org.cerebrum.domain.admission.DiseaseIncidence;
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

public class DiseaseIncidenceActionBase extends BaseAction<DiseaseIncidence>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DiseaseIncidence diseaseIncidence;

	@DataModel
	private List<DiseaseIncidence> diseaseIncidenceList;

	@Factory("diseaseIncidenceList")
	@Observer("archivedDiseaseIncidence")
	public void findRecords() {
		search();
	}

	public DiseaseIncidence getEntity() {
		return diseaseIncidence;
	}

	@Override
	public void setEntity(DiseaseIncidence t) {
		this.diseaseIncidence = t;
	}

	@Override
	public void setEntityList(List<DiseaseIncidence> list) {
		this.diseaseIncidenceList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (diseaseIncidence.getDisease() != null) {
			criteria = criteria.add(Restrictions.eq("disease.id",
					diseaseIncidence.getDisease().getId()));
		}

		if (diseaseIncidence.getAdmission() != null) {
			criteria = criteria.add(Restrictions.eq("admission.id",
					diseaseIncidence.getAdmission().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<DiseaseIncidence> getEntityList() {
		if (diseaseIncidenceList == null) {
			findRecords();
		}
		return diseaseIncidenceList;
	}

}
