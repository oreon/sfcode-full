package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.AgentHistory;

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

public class AgentHistoryActionBase extends BaseAction<AgentHistory>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private AgentHistory agentHistory;

	@DataModel
	private List<AgentHistory> agentHistoryRecordList;

	public void setAgentHistoryId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getAgentHistoryId() {
		return (Long) getId();
	}

	//@Factory("agentHistoryRecordList")
	//@Observer("archivedAgentHistory")
	public void findRecords() {
		//search();
	}

	public AgentHistory getEntity() {
		return agentHistory;
	}

	@Override
	public void setEntity(AgentHistory t) {
		this.agentHistory = t;
		loadAssociations();
	}

	public AgentHistory getAgentHistory() {
		return getInstance();
	}

	@Override
	protected AgentHistory createInstance() {
		return new AgentHistory();
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

	public AgentHistory getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setAgentHistory(AgentHistory t) {
		this.agentHistory = t;
		loadAssociations();
	}

	@Override
	public Class<AgentHistory> getEntityClass() {
		return AgentHistory.class;
	}

	@Override
	public void setEntityList(List<AgentHistory> list) {
		this.agentHistoryRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<AgentHistory> getEntityList() {
		if (agentHistoryRecordList == null) {
			findRecords();
		}
		return agentHistoryRecordList;
	}

}
