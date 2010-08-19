package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateFirm;

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

public abstract class RealEstateFirmActionBase
		extends
			com.nas.recovery.web.action.loan.CompanyAction<RealEstateFirm>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RealEstateFirm realEstateFirm;

	@In(create = true, value = "masterAgentList")
	com.nas.recovery.web.action.realestate.MasterAgentListQuery masterAgentList;

	@DataModel
	private List<RealEstateFirm> realEstateFirmRecordList;

	public void setRealEstateFirmId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getRealEstateFirmId() {
		return (Long) getId();
	}

	//@Factory("realEstateFirmRecordList")
	//@Observer("archivedRealEstateFirm")
	public void findRecords() {
		//search();
	}

	public RealEstateFirm getEntity() {
		return realEstateFirm;
	}

	@Override
	public void setEntity(RealEstateFirm t) {
		this.realEstateFirm = t;
		loadAssociations();
	}

	public RealEstateFirm getRealEstateFirm() {
		return getInstance();
	}

	@Override
	protected RealEstateFirm createInstance() {
		return new RealEstateFirm();
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

	public RealEstateFirm getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setRealEstateFirm(RealEstateFirm t) {
		this.realEstateFirm = t;
		loadAssociations();
	}

	@Override
	public Class<RealEstateFirm> getEntityClass() {
		return RealEstateFirm.class;
	}

	@Override
	public void setEntityList(List<RealEstateFirm> list) {
		this.realEstateFirmRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		try {

			masterAgentList.getMasterAgent().setRealEstateFirm(getInstance());

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}

	}

	public void updateAssociations() {

		com.nas.recovery.domain.realestate.MasterAgent masterAgent = (com.nas.recovery.domain.realestate.MasterAgent) org.jboss.seam.Component
				.getInstance("masterAgent");
		masterAgent.setRealEstateFirm(realEstateFirm);
		events.raiseTransactionSuccessEvent("archivedMasterAgent");

	}

	public void updateComposedAssociations() {
	}

	public List<RealEstateFirm> getEntityList() {
		if (realEstateFirmRecordList == null) {
			findRecords();
		}
		return realEstateFirmRecordList;
	}

}
