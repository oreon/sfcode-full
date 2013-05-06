package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.BedStay;

import org.witchcraft.seam.action.BaseAction;

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

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class BedStayActionBase extends BaseAction<BedStay>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private BedStay bedStay;

	@In(create = true, value = "admissionAction")
	com.oreon.cerebrum.web.action.patient.AdmissionAction admissionAction;

	@In(create = true, value = "bedAction")
	com.oreon.cerebrum.web.action.facility.BedAction bedAction;

	public void setBedStayId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		bedStay = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setBedStayIdForModalDlg(Long id) {
		setId(id);
		bedStay = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setAdmissionId(Long id) {

		if (id != null && id > 0)
			getInstance().setAdmission(admissionAction.loadFromId(id));

	}

	public Long getAdmissionId() {
		if (getInstance().getAdmission() != null)
			return getInstance().getAdmission().getId();
		return 0L;
	}

	public void setBedId(Long id) {

		if (id != null && id > 0)
			getInstance().setBed(bedAction.loadFromId(id));

	}

	public Long getBedId() {
		if (getInstance().getBed() != null)
			return getInstance().getBed().getId();
		return 0L;
	}

	public Long getBedStayId() {
		return (Long) getId();
	}

	public BedStay getEntity() {
		return bedStay;
	}

	//@Override
	public void setEntity(BedStay t) {
		this.bedStay = t;
		loadAssociations();
	}

	public BedStay getBedStay() {
		return (BedStay) getInstance();
	}

	@Override
	protected BedStay createInstance() {
		BedStay instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.patient.Admission admission = admissionAction
				.getDefinedInstance();
		if (admission != null && isNew()) {
			getInstance().setAdmission(admission);
		}

		com.oreon.cerebrum.facility.Bed bed = bedAction.getDefinedInstance();
		if (bed != null && isNew()) {
			getInstance().setBed(bed);
		}

	}

	public boolean isWired() {
		return true;
	}

	public BedStay getDefinedInstance() {
		return (BedStay) (isIdDefined() ? getInstance() : null);
	}

	public void setBedStay(BedStay t) {
		this.bedStay = t;
		if (bedStay != null)
			setBedStayId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<BedStay> getEntityClass() {
		return BedStay.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (bedStay.getAdmission() != null) {
			criteria = criteria.add(Restrictions.eq("admission.id", bedStay
					.getAdmission().getId()));
		}

		if (bedStay.getBed() != null) {
			criteria = criteria.add(Restrictions.eq("bed.id", bedStay.getBed()
					.getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (bedStay.getAdmission() != null) {
			admissionAction.setInstance(getInstance().getAdmission());
			admissionAction.loadAssociations();
		}

		if (bedStay.getBed() != null) {
			bedAction.setInstance(getInstance().getBed());
			bedAction.loadAssociations();
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewBedStay() {
		load(currentEntityId);
		return "viewBedStay";
	}

}
