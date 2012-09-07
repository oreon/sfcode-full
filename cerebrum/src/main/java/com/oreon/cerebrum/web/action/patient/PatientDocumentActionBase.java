package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.PatientDocument;

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

public abstract class PatientDocumentActionBase
		extends
			BaseAction<PatientDocument> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private PatientDocument patientDocument;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@DataModel
	private List<PatientDocument> patientDocumentRecordList;

	public void setPatientDocumentId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPatientDocumentIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public Long getPatientDocumentId() {
		return (Long) getId();
	}

	public PatientDocument getEntity() {
		return patientDocument;
	}

	//@Override
	public void setEntity(PatientDocument t) {
		this.patientDocument = t;
		loadAssociations();
	}

	public PatientDocument getPatientDocument() {
		return (PatientDocument) getInstance();
	}

	@Override
	protected PatientDocument createInstance() {
		PatientDocument instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public boolean isWired() {
		return true;
	}

	public PatientDocument getDefinedInstance() {
		return (PatientDocument) (isIdDefined() ? getInstance() : null);
	}

	public void setPatientDocument(PatientDocument t) {
		this.patientDocument = t;
		if (patientDocument != null)
			setPatientDocumentId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<PatientDocument> getEntityClass() {
		return PatientDocument.class;
	}

	public String downloadFile(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getFile());
		return "success";
	}

	public void fileUploadListener(UploadEvent event) throws Exception {
		UploadItem uploadItem = event.getUploadItem();
		if (getInstance().getFile() == null)
			getInstance().setFile(new FileAttachment());
		getInstance().getFile().setName(uploadItem.getFileName());
		getInstance().getFile().setContentType(uploadItem.getContentType());
		getInstance().getFile().setData(
				FileUtils.readFileToByteArray(uploadItem.getFile()));
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (patientDocument.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id",
					patientDocument.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (patientDocument.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewPatientDocument() {
		load(currentEntityId);
		return "viewPatientDocument";
	}

}
