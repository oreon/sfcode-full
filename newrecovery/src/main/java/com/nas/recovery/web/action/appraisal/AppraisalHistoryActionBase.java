package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.AppraisalHistory;

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

public class AppraisalHistoryActionBase extends BaseAction<AppraisalHistory>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private AppraisalHistory appraisalHistory;

	@DataModel
	private List<AppraisalHistory> appraisalHistoryRecordList;

	public void setAppraisalHistoryId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getAppraisalHistoryId() {
		return (Long) getId();
	}

	//@Factory("appraisalHistoryRecordList")
	//@Observer("archivedAppraisalHistory")
	public void findRecords() {
		//search();
	}

	public AppraisalHistory getEntity() {
		return appraisalHistory;
	}

	@Override
	public void setEntity(AppraisalHistory t) {
		this.appraisalHistory = t;
		loadAssociations();
	}

	public AppraisalHistory getAppraisalHistory() {
		return getInstance();
	}

	@Override
	protected AppraisalHistory createInstance() {
		return new AppraisalHistory();
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

	public AppraisalHistory getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setAppraisalHistory(AppraisalHistory t) {
		this.appraisalHistory = t;
		loadAssociations();
	}

	@Override
	public Class<AppraisalHistory> getEntityClass() {
		return AppraisalHistory.class;
	}

	@Override
	public void setEntityList(List<AppraisalHistory> list) {
		this.appraisalHistoryRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<AppraisalHistory> getEntityList() {
		if (appraisalHistoryRecordList == null) {
			findRecords();
		}
		return appraisalHistoryRecordList;
	}

}
