package com.oreon.cerebrum.web.action.appointment;

import com.oreon.cerebrum.appointment.PrescribedTest;

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

public abstract class PrescribedTestActionBase
		extends
			BaseAction<PrescribedTest> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private PrescribedTest prescribedTest;

	@In(create = true, value = "dxTestAction")
	com.oreon.cerebrum.web.action.appointment.DxTestAction dxTestAction;

	@In(create = true, value = "encounterAction")
	com.oreon.cerebrum.web.action.appointment.EncounterAction encounterAction;

	@DataModel
	private List<PrescribedTest> prescribedTestRecordList;

	public void setPrescribedTestId(Long id) {
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
	public void setPrescribedTestIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setDxTestId(Long id) {

		if (id != null && id > 0)
			getInstance().setDxTest(dxTestAction.loadFromId(id));

	}

	public Long getDxTestId() {
		if (getInstance().getDxTest() != null)
			return getInstance().getDxTest().getId();
		return 0L;
	}

	public void setEncounterId(Long id) {

		if (id != null && id > 0)
			getInstance().setEncounter(encounterAction.loadFromId(id));

	}

	public Long getEncounterId() {
		if (getInstance().getEncounter() != null)
			return getInstance().getEncounter().getId();
		return 0L;
	}

	public Long getPrescribedTestId() {
		return (Long) getId();
	}

	public PrescribedTest getEntity() {
		return prescribedTest;
	}

	//@Override
	public void setEntity(PrescribedTest t) {
		this.prescribedTest = t;
		loadAssociations();
	}

	public PrescribedTest getPrescribedTest() {
		return (PrescribedTest) getInstance();
	}

	@Override
	protected PrescribedTest createInstance() {
		PrescribedTest instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.appointment.DxTest dxTest = dxTestAction
				.getDefinedInstance();
		if (dxTest != null && isNew()) {
			getInstance().setDxTest(dxTest);
		}

		com.oreon.cerebrum.appointment.Encounter encounter = encounterAction
				.getDefinedInstance();
		if (encounter != null && isNew()) {
			getInstance().setEncounter(encounter);
		}

	}

	public boolean isWired() {
		return true;
	}

	public PrescribedTest getDefinedInstance() {
		return (PrescribedTest) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescribedTest(PrescribedTest t) {
		this.prescribedTest = t;
		if (prescribedTest != null)
			setPrescribedTestId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<PrescribedTest> getEntityClass() {
		return PrescribedTest.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (prescribedTest.getDxTest() != null) {
			criteria = criteria.add(Restrictions.eq("dxTest.id", prescribedTest
					.getDxTest().getId()));
		}

		if (prescribedTest.getEncounter() != null) {
			criteria = criteria.add(Restrictions.eq("encounter.id",
					prescribedTest.getEncounter().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (prescribedTest.getDxTest() != null) {
			dxTestAction.setInstance(getInstance().getDxTest());
		}

		if (prescribedTest.getEncounter() != null) {
			encounterAction.setInstance(getInstance().getEncounter());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewPrescribedTest() {
		load(currentEntityId);
		return "viewPrescribedTest";
	}

}
