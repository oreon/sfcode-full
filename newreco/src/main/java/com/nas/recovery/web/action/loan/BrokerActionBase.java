package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.Broker;

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

public abstract class BrokerActionBase
		extends
			com.nas.recovery.web.action.loan.PersonAction<Broker>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Broker broker;

	@DataModel
	private List<Broker> brokerRecordList;

	public void setBrokerId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getBrokerId() {
		return (Long) getId();
	}

	//@Factory("brokerRecordList")
	//@Observer("archivedBroker")
	public void findRecords() {
		//search();
	}

	public Broker getEntity() {
		return broker;
	}

	@Override
	public void setEntity(Broker t) {
		this.broker = t;
		loadAssociations();
	}

	public Broker getBroker() {
		return getInstance();
	}

	@Override
	protected Broker createInstance() {
		return new Broker();
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

	public Broker getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setBroker(Broker t) {
		this.broker = t;
		loadAssociations();
	}

	@Override
	public Class<Broker> getEntityClass() {
		return Broker.class;
	}

	@Override
	public void setEntityList(List<Broker> list) {
		this.brokerRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<Broker> getEntityList() {
		if (brokerRecordList == null) {
			findRecords();
		}
		return brokerRecordList;
	}

}
