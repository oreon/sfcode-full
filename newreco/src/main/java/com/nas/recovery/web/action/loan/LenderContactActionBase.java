package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.LenderContact;

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

public abstract class LenderContactActionBase
		extends
			com.nas.recovery.web.action.loan.PersonAction<LenderContact>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private LenderContact lenderContact;

	@In(create = true, value = "lenderAction")
	com.nas.recovery.web.action.loan.LenderAction lenderAction;

	@DataModel
	private List<LenderContact> lenderContactRecordList;

	public void setLenderContactId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setLenderId(Long id) {
		if (id != null && id > 0)
			getInstance().setLender(lenderAction.loadFromId(id));
	}

	public Long getLenderId() {
		if (getInstance().getLender() != null)
			return getInstance().getLender().getId();
		return 0L;
	}

	public Long getLenderContactId() {
		return (Long) getId();
	}

	//@Factory("lenderContactRecordList")
	//@Observer("archivedLenderContact")
	public void findRecords() {
		//search();
	}

	public LenderContact getEntity() {
		return lenderContact;
	}

	@Override
	public void setEntity(LenderContact t) {
		this.lenderContact = t;
		loadAssociations();
	}

	public LenderContact getLenderContact() {
		return getInstance();
	}

	@Override
	protected LenderContact createInstance() {
		return new LenderContact();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.loan.Lender lender = lenderAction
				.getDefinedInstance();
		if (lender != null) {
			getInstance().setLender(lender);
		}

	}

	public boolean isWired() {
		return true;
	}

	public LenderContact getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setLenderContact(LenderContact t) {
		this.lenderContact = t;
		loadAssociations();
	}

	@Override
	public Class<LenderContact> getEntityClass() {
		return LenderContact.class;
	}

	@Override
	public void setEntityList(List<LenderContact> list) {
		this.lenderContactRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (lenderContact.getLender() != null) {
			criteria = criteria.add(Restrictions.eq("lender.id", lenderContact
					.getLender().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (lenderContact.getLender() != null) {
			lenderAction.setInstance(getInstance().getLender());
		}

	}

	public void updateAssociations() {

	}

	public List<LenderContact> getEntityList() {
		if (lenderContactRecordList == null) {
			findRecords();
		}
		return lenderContactRecordList;
	}

}
