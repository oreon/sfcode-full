package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Process;

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

public class ProcessActionBase extends BaseAction<Process>
		implements
			java.io.Serializable {


	@DataModel
	private List<Process> processRecordList;

	public void setProcessId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getProcessId() {
		return (Long) getId();
	}

	//@Factory("processRecordList")
	//@Observer("archivedProcess")
	public void findRecords() {
		//search();
	}


	public Process getProcess() {
		return getInstance();
	}

	@Override
	protected Process createInstance() {
		return new Process();
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

	public Process getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setProcess(Process t) {
		//this.process = t;
		loadAssociations();
	}

	@Override
	public Class<Process> getEntityClass() {
		return Process.class;
	}

	@Override
	public void setEntityList(List<Process> list) {
		this.processRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Process> getEntityList() {
		if (processRecordList == null) {
			findRecords();
		}
		return processRecordList;
	}

	@Override
	public Process getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntity(Process t) {
		// TODO Auto-generated method stub
		
	}

}
