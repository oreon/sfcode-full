package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.Lender;

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

public class LenderActionBase extends BaseAction<Lender>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Lender lender;

	@In(create = true, value = "lenderContactList")
	com.nas.recovery.web.action.loan.LenderContactListQuery lenderContactList;

	@DataModel
	private List<Lender> lenderRecordList;

	public void setLenderId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getLenderId() {
		return (Long) getId();
	}

	//@Factory("lenderRecordList")
	//@Observer("archivedLender")
	public void findRecords() {
		//search();
	}

	public Lender getEntity() {
		return lender;
	}

	@Override
	public void setEntity(Lender t) {
		this.lender = t;
		loadAssociations();
	}

	public Lender getLender() {
		return getInstance();
	}

	@Override
	protected Lender createInstance() {
		return new Lender();
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

	public Lender getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setLender(Lender t) {
		this.lender = t;
		loadAssociations();
	}

	@Override
	public Class<Lender> getEntityClass() {
		return Lender.class;
	}

	@Override
	public void setEntityList(List<Lender> list) {
		this.lenderRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		try {

			lenderContactList.getLenderContact().setLender(getInstance());

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}

	}

	public void updateAssociations() {

		com.nas.recovery.domain.loan.LenderContact lenderContact = (com.nas.recovery.domain.loan.LenderContact) org.jboss.seam.Component
				.getInstance("lenderContact");
		lenderContact.setLender(lender);
		events.raiseTransactionSuccessEvent("archivedLenderContact");

	}

	public List<Lender> getEntityList() {
		if (lenderRecordList == null) {
			findRecords();
		}
		return lenderRecordList;
	}

	public String register() {

		return null;

	}

	public String retrieveCredentials() {

		return null;

	}

	public String login() {

		return null;

	}

}
