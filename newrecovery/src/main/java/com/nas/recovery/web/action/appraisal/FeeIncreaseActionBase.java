package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.FeeIncrease;

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

public class FeeIncreaseActionBase extends BaseAction<FeeIncrease>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FeeIncrease feeIncrease;

	@DataModel
	private List<FeeIncrease> feeIncreaseRecordList;

	public void setFeeIncreaseId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getFeeIncreaseId() {
		return (Long) getId();
	}

	//@Factory("feeIncreaseRecordList")
	//@Observer("archivedFeeIncrease")
	public void findRecords() {
		//search();
	}

	public FeeIncrease getEntity() {
		return feeIncrease;
	}

	@Override
	public void setEntity(FeeIncrease t) {
		this.feeIncrease = t;
		loadAssociations();
	}

	public FeeIncrease getFeeIncrease() {
		return getInstance();
	}

	@Override
	protected FeeIncrease createInstance() {
		return new FeeIncrease();
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

	public FeeIncrease getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setFeeIncrease(FeeIncrease t) {
		this.feeIncrease = t;
		loadAssociations();
	}

	@Override
	public Class<FeeIncrease> getEntityClass() {
		return FeeIncrease.class;
	}

	@Override
	public void setEntityList(List<FeeIncrease> list) {
		this.feeIncreaseRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<FeeIncrease> getEntityList() {
		if (feeIncreaseRecordList == null) {
			findRecords();
		}
		return feeIncreaseRecordList;
	}

}
