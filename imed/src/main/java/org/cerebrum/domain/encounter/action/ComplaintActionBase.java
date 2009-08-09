package org.cerebrum.domain.encounter.action;

import org.cerebrum.domain.encounter.Complaint;
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

public class ComplaintActionBase extends BaseAction<Complaint>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Complaint complaint;

	@DataModel
	private List<Complaint> complaintList;

	@Factory("complaintList")
	@Observer("archivedComplaint")
	public void findRecords() {
		search();
	}

	public Complaint getEntity() {
		return complaint;
	}

	@Override
	public void setEntity(Complaint t) {
		this.complaint = t;
	}

	@Override
	public void setEntityList(List<Complaint> list) {
		this.complaintList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (complaint.getEncounter() != null) {
			criteria = criteria.add(Restrictions.eq("encounter.id", complaint
					.getEncounter().getId()));
		}

		if (complaint.getSymptom() != null) {
			criteria = criteria.add(Restrictions.eq("symptom.id", complaint
					.getSymptom().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Complaint> getEntityList() {
		if (complaintList == null) {
			findRecords();
		}
		return complaintList;
	}

}
