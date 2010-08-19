package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Bankruptcy;

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

public abstract class BankruptcyActionBase extends BaseAction<Bankruptcy>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Bankruptcy bankruptcy;

	@In(create = true, value = "legalAction")
	com.nas.recovery.web.action.legal.LegalAction legalAction;

	@DataModel
	private List<Bankruptcy> bankruptcyRecordList;

	public void setBankruptcyId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setLegalId(Long id) {
		if (id != null && id > 0)
			getInstance().setLegal(legalAction.loadFromId(id));
	}

	public Long getLegalId() {
		if (getInstance().getLegal() != null)
			return getInstance().getLegal().getId();
		return 0L;
	}

	public Long getBankruptcyId() {
		return (Long) getId();
	}

	//@Factory("bankruptcyRecordList")
	//@Observer("archivedBankruptcy")
	public void findRecords() {
		//search();
	}

	public Bankruptcy getEntity() {
		return bankruptcy;
	}

	@Override
	public void setEntity(Bankruptcy t) {
		this.bankruptcy = t;
		loadAssociations();
	}

	public Bankruptcy getBankruptcy() {
		return getInstance();
	}

	@Override
	protected Bankruptcy createInstance() {
		return new Bankruptcy();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.legal.Legal legal = legalAction
				.getDefinedInstance();
		if (legal != null) {
			getInstance().setLegal(legal);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Bankruptcy getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setBankruptcy(Bankruptcy t) {
		this.bankruptcy = t;
		loadAssociations();
	}

	@Override
	public Class<Bankruptcy> getEntityClass() {
		return Bankruptcy.class;
	}

	@Override
	public void setEntityList(List<Bankruptcy> list) {
		this.bankruptcyRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (bankruptcy.getLegal() != null) {
			criteria = criteria.add(Restrictions.eq("legal.id", bankruptcy
					.getLegal().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (bankruptcy.getLegal() != null) {
			legalAction.setInstance(getInstance().getLegal());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<Bankruptcy> getEntityList() {
		if (bankruptcyRecordList == null) {
			findRecords();
		}
		return bankruptcyRecordList;
	}

}
