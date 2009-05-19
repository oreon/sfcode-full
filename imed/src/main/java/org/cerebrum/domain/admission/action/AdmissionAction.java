package org.cerebrum.domain.admission.action;

import org.cerebrum.domain.admission.Admission;
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

import org.cerebrum.domain.admission.DiseaseIncidence;

@Scope(ScopeType.CONVERSATION)
@Name("admissionAction")
public class AdmissionAction extends BaseAction<Admission>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Admission admission;

	@DataModel
	private List<Admission> admissionList;

	@Factory("admissionList")
	@Observer("archivedAdmission")
	public void findRecords() {
		search();
	}

	public Admission getEntity() {
		return admission;
	}

	@Override
	public void setEntity(Admission t) {
		this.admission = t;
	}

	@Override
	public void setEntityList(List<Admission> list) {
		this.admissionList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (admission.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", admission
					.getPatient().getId()));
		}

		if (admission.getPrescription() != null) {
			criteria = criteria.add(Restrictions.eq("prescription.id",
					admission.getPrescription().getId()));
		}

		if (admission.getBedAllocation() != null) {
			criteria = criteria.add(Restrictions.eq("bedAllocation.id",
					admission.getBedAllocation().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<DiseaseIncidence> listComplaints;

	void initListComplaints() {
		listComplaints = new ArrayList<DiseaseIncidence>();
		if (admission.getComplaints().isEmpty()) {

		} else
			listComplaints.addAll(admission.getComplaints());
	}

	public List<DiseaseIncidence> getListComplaints() {
		if (listComplaints == null) {
			initListComplaints();
		}
		return listComplaints;
	}

	public void setListComplaints(List<DiseaseIncidence> listComplaints) {
		this.listComplaints = listComplaints;
	}

	public void deleteComplaints(DiseaseIncidence complaints) {
		listComplaints.remove(complaints);
	}

	@Begin(join = true)
	public void addComplaints() {
		DiseaseIncidence complaints = new DiseaseIncidence();

		complaints.setAdmission(admission);

		listComplaints.add(complaints);
	}

	public void updateComposedAssociations() {

		admission.getComplaints().clear();
		admission.getComplaints().addAll(listComplaints);

	}

	public List<Admission> getEntityList() {
		if (admissionList == null) {
			findRecords();
		}
		return admissionList;
	}

}
