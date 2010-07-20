package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.AppraisalReports;

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

public class AppraisalReportsActionBase extends BaseAction<AppraisalReports>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private AppraisalReports appraisalReports;

	@DataModel
	private List<AppraisalReports> appraisalReportsRecordList;

	public void setAppraisalReportsId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getAppraisalReportsId() {
		return (Long) getId();
	}

	//@Factory("appraisalReportsRecordList")
	//@Observer("archivedAppraisalReports")
	public void findRecords() {
		//search();
	}

	public AppraisalReports getEntity() {
		return appraisalReports;
	}

	@Override
	public void setEntity(AppraisalReports t) {
		this.appraisalReports = t;
		loadAssociations();
	}

	public AppraisalReports getAppraisalReports() {
		return getInstance();
	}

	@Override
	protected AppraisalReports createInstance() {
		return new AppraisalReports();
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

	public AppraisalReports getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setAppraisalReports(AppraisalReports t) {
		this.appraisalReports = t;
		loadAssociations();
	}

	@Override
	public Class<AppraisalReports> getEntityClass() {
		return AppraisalReports.class;
	}

	@Override
	public void setEntityList(List<AppraisalReports> list) {
		this.appraisalReportsRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<AppraisalReports> getEntityList() {
		if (appraisalReportsRecordList == null) {
			findRecords();
		}
		return appraisalReportsRecordList;
	}

}
