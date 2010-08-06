package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.MasterAgent;

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

public abstract class MasterAgentActionBase
		extends
			com.nas.recovery.web.action.loan.PersonAction<MasterAgent>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MasterAgent masterAgent;

	@In(create = true, value = "realEstateFirmAction")
	com.nas.recovery.web.action.realestate.RealEstateFirmAction realEstateFirmAction;

	@DataModel
	private List<MasterAgent> masterAgentRecordList;

	public void setMasterAgentId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setRealEstateFirmId(Long id) {
		if (id != null && id > 0)
			getInstance()
					.setRealEstateFirm(realEstateFirmAction.loadFromId(id));
	}

	public Long getRealEstateFirmId() {
		if (getInstance().getRealEstateFirm() != null)
			return getInstance().getRealEstateFirm().getId();
		return 0L;
	}

	public Long getMasterAgentId() {
		return (Long) getId();
	}

	//@Factory("masterAgentRecordList")
	//@Observer("archivedMasterAgent")
	public void findRecords() {
		//search();
	}

	public MasterAgent getEntity() {
		return masterAgent;
	}

	@Override
	public void setEntity(MasterAgent t) {
		this.masterAgent = t;
		loadAssociations();
	}

	public MasterAgent getMasterAgent() {
		return getInstance();
	}

	@Override
	protected MasterAgent createInstance() {
		return new MasterAgent();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.realestate.RealEstateFirm realEstateFirm = realEstateFirmAction
				.getDefinedInstance();
		if (realEstateFirm != null) {
			getInstance().setRealEstateFirm(realEstateFirm);
		}

	}

	public boolean isWired() {
		return true;
	}

	public MasterAgent getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setMasterAgent(MasterAgent t) {
		this.masterAgent = t;
		loadAssociations();
	}

	@Override
	public Class<MasterAgent> getEntityClass() {
		return MasterAgent.class;
	}

	@Override
	public void setEntityList(List<MasterAgent> list) {
		this.masterAgentRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (masterAgent.getRealEstateFirm() != null) {
			criteria = criteria.add(Restrictions.eq("realEstateFirm.id",
					masterAgent.getRealEstateFirm().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (masterAgent.getRealEstateFirm() != null) {
			realEstateFirmAction.setInstance(getInstance().getRealEstateFirm());
		}

	}

	public void updateAssociations() {

	}

	public List<MasterAgent> getEntityList() {
		if (masterAgentRecordList == null) {
			findRecords();
		}
		return masterAgentRecordList;
	}

}
