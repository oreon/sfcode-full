package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.MortgageInsurer;

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

public abstract class MortgageInsurerActionBase
		extends
			com.nas.recovery.web.action.loan.CompanyAction<MortgageInsurer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MortgageInsurer mortgageInsurer;

	@DataModel
	private List<MortgageInsurer> mortgageInsurerRecordList;

	public void setMortgageInsurerId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getMortgageInsurerId() {
		return (Long) getId();
	}

	//@Factory("mortgageInsurerRecordList")
	//@Observer("archivedMortgageInsurer")
	public void findRecords() {
		//search();
	}

	public MortgageInsurer getEntity() {
		return mortgageInsurer;
	}

	@Override
	public void setEntity(MortgageInsurer t) {
		this.mortgageInsurer = t;
		loadAssociations();
	}

	public MortgageInsurer getMortgageInsurer() {
		return getInstance();
	}

	@Override
	protected MortgageInsurer createInstance() {
		return new MortgageInsurer();
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

	public MortgageInsurer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setMortgageInsurer(MortgageInsurer t) {
		this.mortgageInsurer = t;
		loadAssociations();
	}

	@Override
	public Class<MortgageInsurer> getEntityClass() {
		return MortgageInsurer.class;
	}

	@Override
	public void setEntityList(List<MortgageInsurer> list) {
		this.mortgageInsurerRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<MortgageInsurer> getEntityList() {
		if (mortgageInsurerRecordList == null) {
			findRecords();
		}
		return mortgageInsurerRecordList;
	}

}
