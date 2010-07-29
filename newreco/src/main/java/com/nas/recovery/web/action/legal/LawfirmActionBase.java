package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Lawfirm;

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

public abstract class LawfirmActionBase
		extends
			com.nas.recovery.web.action.loan.CompanyAction<Lawfirm>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Lawfirm lawfirm;

	@In(create = true, value = "lawyerList")
	com.nas.recovery.web.action.legal.LawyerListQuery lawyerList;

	@DataModel
	private List<Lawfirm> lawfirmRecordList;

	public void setLawfirmId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getLawfirmId() {
		return (Long) getId();
	}

	//@Factory("lawfirmRecordList")
	//@Observer("archivedLawfirm")
	public void findRecords() {
		//search();
	}

	public Lawfirm getEntity() {
		return lawfirm;
	}

	@Override
	public void setEntity(Lawfirm t) {
		this.lawfirm = t;
		loadAssociations();
	}

	public Lawfirm getLawfirm() {
		return getInstance();
	}

	@Override
	protected Lawfirm createInstance() {
		return new Lawfirm();
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

	public Lawfirm getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setLawfirm(Lawfirm t) {
		this.lawfirm = t;
		loadAssociations();
	}

	@Override
	public Class<Lawfirm> getEntityClass() {
		return Lawfirm.class;
	}

	@Override
	public void setEntityList(List<Lawfirm> list) {
		this.lawfirmRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		try {

			lawyerList.getLawyer().setLawfirm(getInstance());

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}

	}

	public void updateAssociations() {

		com.nas.recovery.domain.legal.Lawyer lawyer = (com.nas.recovery.domain.legal.Lawyer) org.jboss.seam.Component
				.getInstance("lawyer");
		lawyer.setLawfirm(lawfirm);
		events.raiseTransactionSuccessEvent("archivedLawyer");

	}

	public List<Lawfirm> getEntityList() {
		if (lawfirmRecordList == null) {
			findRecords();
		}
		return lawfirmRecordList;
	}

}
