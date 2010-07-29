package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.Appraisal;

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

public abstract class AppraisalActionBase extends BaseAction<Appraisal>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Appraisal appraisal;

	@DataModel
	private List<Appraisal> appraisalRecordList;

	public void setAppraisalId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getAppraisalId() {
		return (Long) getId();
	}

	//@Factory("appraisalRecordList")
	//@Observer("archivedAppraisal")
	public void findRecords() {
		//search();
	}

	public Appraisal getEntity() {
		return appraisal;
	}

	@Override
	public void setEntity(Appraisal t) {
		this.appraisal = t;
		loadAssociations();
	}

	public Appraisal getAppraisal() {
		return getInstance();
	}

	@Override
	protected Appraisal createInstance() {
		return new Appraisal();
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

	public Appraisal getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setAppraisal(Appraisal t) {
		this.appraisal = t;
		loadAssociations();
	}

	@Override
	public Class<Appraisal> getEntityClass() {
		return Appraisal.class;
	}

	@Override
	public void setEntityList(List<Appraisal> list) {
		this.appraisalRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Appraisal> getEntityList() {
		if (appraisalRecordList == null) {
			findRecords();
		}
		return appraisalRecordList;
	}

}