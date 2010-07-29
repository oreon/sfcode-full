package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.ClosingProcess;

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

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class ClosingProcessActionBase
		extends
			com.nas.recovery.web.action.legal.ProcessAction<ClosingProcess>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ClosingProcess closingProcess;

	@DataModel
	private List<ClosingProcess> closingProcessRecordList;

	public void setClosingProcessId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getClosingProcessId() {
		return (Long) getId();
	}

	//@Factory("closingProcessRecordList")
	//@Observer("archivedClosingProcess")
	public void findRecords() {
		//search();
	}

	public ClosingProcess getEntity() {
		return closingProcess;
	}

	@Override
	public void setEntity(ClosingProcess t) {
		this.closingProcess = t;
		loadAssociations();
	}

	public ClosingProcess getClosingProcess() {
		return getInstance();
	}

	@Override
	protected ClosingProcess createInstance() {
		return new ClosingProcess();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public ClosingProcess getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setClosingProcess(ClosingProcess t) {
		this.closingProcess = t;
		loadAssociations();
	}

	@Override
	public Class<ClosingProcess> getEntityClass() {
		return ClosingProcess.class;
	}

	@Override
	public void setEntityList(List<ClosingProcess> list) {
		this.closingProcessRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<ClosingProcess> getEntityList() {
		if (closingProcessRecordList == null) {
			findRecords();
		}
		return closingProcessRecordList;
	}

}
