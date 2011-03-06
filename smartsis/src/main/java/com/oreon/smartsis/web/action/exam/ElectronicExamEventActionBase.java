package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.ElectronicExamEvent;

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

import com.oreon.smartsis.exam.ElectronicExamInstance;

public abstract class ElectronicExamEventActionBase
		extends
			BaseAction<ElectronicExamEvent> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ElectronicExamEvent electronicExamEvent;

	@In(create = true, value = "electronicExamAction")
	com.oreon.smartsis.web.action.exam.ElectronicExamAction electronicExamAction;

	@DataModel
	private List<ElectronicExamEvent> electronicExamEventRecordList;

	public void setElectronicExamEventId(Long id) {
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
	public void setElectronicExamEventIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setElectronicExamId(Long id) {

		if (id != null && id > 0)
			getInstance()
					.setElectronicExam(electronicExamAction.loadFromId(id));

	}

	public Long getElectronicExamId() {
		if (getInstance().getElectronicExam() != null)
			return getInstance().getElectronicExam().getId();
		return 0L;
	}

	public Long getElectronicExamEventId() {
		return (Long) getId();
	}

	public ElectronicExamEvent getEntity() {
		return electronicExamEvent;
	}

	//@Override
	public void setEntity(ElectronicExamEvent t) {
		this.electronicExamEvent = t;
		loadAssociations();
	}

	public ElectronicExamEvent getElectronicExamEvent() {
		return (ElectronicExamEvent) getInstance();
	}

	@Override
	protected ElectronicExamEvent createInstance() {
		return new ElectronicExamEvent();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.exam.ElectronicExam electronicExam = electronicExamAction
				.getDefinedInstance();
		if (electronicExam != null && isNew()) {
			getInstance().setElectronicExam(electronicExam);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ElectronicExamEvent getDefinedInstance() {
		return (ElectronicExamEvent) (isIdDefined() ? getInstance() : null);
	}

	public void setElectronicExamEvent(ElectronicExamEvent t) {
		this.electronicExamEvent = t;
		loadAssociations();
	}

	@Override
	public Class<ElectronicExamEvent> getEntityClass() {
		return ElectronicExamEvent.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (electronicExamEvent.getElectronicExam() != null) {
			criteria = criteria.add(Restrictions.eq("electronicExam.id",
					electronicExamEvent.getElectronicExam().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (electronicExamEvent.getElectronicExam() != null) {
			electronicExamAction.setInstance(getInstance().getElectronicExam());
		}

		initListElectronicExamInstances();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.exam.ElectronicExamInstance> listElectronicExamInstances = new ArrayList<com.oreon.smartsis.exam.ElectronicExamInstance>();

	void initListElectronicExamInstances() {

		if (listElectronicExamInstances.isEmpty())
			listElectronicExamInstances.addAll(getInstance()
					.getElectronicExamInstances());

	}

	public List<com.oreon.smartsis.exam.ElectronicExamInstance> getListElectronicExamInstances() {

		prePopulateListElectronicExamInstances();
		return listElectronicExamInstances;
	}

	public void prePopulateListElectronicExamInstances() {
	}

	public void setListElectronicExamInstances(
			List<com.oreon.smartsis.exam.ElectronicExamInstance> listElectronicExamInstances) {
		this.listElectronicExamInstances = listElectronicExamInstances;
	}

	public void deleteElectronicExamInstances(int index) {
		listElectronicExamInstances.remove(index);
	}

	@Begin(join = true)
	public void addElectronicExamInstances() {
		initListElectronicExamInstances();
		ElectronicExamInstance electronicExamInstances = new ElectronicExamInstance();

		electronicExamInstances.setElectronicExamEvent(getInstance());

		getListElectronicExamInstances().add(electronicExamInstances);
	}

	public void updateComposedAssociations() {

		if (listElectronicExamInstances != null) {
			getInstance().getElectronicExamInstances().clear();
			getInstance().getElectronicExamInstances().addAll(
					listElectronicExamInstances);
		}
	}

	public void clearLists() {
		listElectronicExamInstances.clear();

	}

}
