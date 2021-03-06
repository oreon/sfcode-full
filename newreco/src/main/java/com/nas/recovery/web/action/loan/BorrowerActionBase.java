package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.Borrower;

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

public abstract class BorrowerActionBase
		extends
			com.nas.recovery.web.action.loan.PersonAction<Borrower>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Borrower borrower;

	@DataModel
	private List<Borrower> borrowerRecordList;

	public void setBorrowerId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getBorrowerId() {
		return (Long) getId();
	}

	//@Factory("borrowerRecordList")
	//@Observer("archivedBorrower")
	public void findRecords() {
		//search();
	}

	public Borrower getEntity() {
		return borrower;
	}

	@Override
	public void setEntity(Borrower t) {
		this.borrower = t;
		loadAssociations();
	}

	public Borrower getBorrower() {
		return getInstance();
	}

	@Override
	protected Borrower createInstance() {
		return new Borrower();
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

	public Borrower getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setBorrower(Borrower t) {
		this.borrower = t;
		loadAssociations();
	}

	@Override
	public Class<Borrower> getEntityClass() {
		return Borrower.class;
	}

	@Override
	public void setEntityList(List<Borrower> list) {
		this.borrowerRecordList = list;
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

	public List<Borrower> getEntityList() {
		if (borrowerRecordList == null) {
			findRecords();
		}
		return borrowerRecordList;
	}

}
