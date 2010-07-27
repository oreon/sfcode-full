package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.TitleInsurer;

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

public class TitleInsurerActionBase extends BaseAction<TitleInsurer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private TitleInsurer titleInsurer;

	@DataModel
	private List<TitleInsurer> titleInsurerRecordList;

	public void setTitleInsurerId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getTitleInsurerId() {
		return (Long) getId();
	}

	//@Factory("titleInsurerRecordList")
	//@Observer("archivedTitleInsurer")
	public void findRecords() {
		//search();
	}

	public TitleInsurer getEntity() {
		return titleInsurer;
	}

	@Override
	public void setEntity(TitleInsurer t) {
		this.titleInsurer = t;
		loadAssociations();
	}

	public TitleInsurer getTitleInsurer() {
		return getInstance();
	}

	@Override
	protected TitleInsurer createInstance() {
		return new TitleInsurer();
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

	public TitleInsurer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setTitleInsurer(TitleInsurer t) {
		this.titleInsurer = t;
		loadAssociations();
	}

	@Override
	public Class<TitleInsurer> getEntityClass() {
		return TitleInsurer.class;
	}

	@Override
	public void setEntityList(List<TitleInsurer> list) {
		this.titleInsurerRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<TitleInsurer> getEntityList() {
		if (titleInsurerRecordList == null) {
			findRecords();
		}
		return titleInsurerRecordList;
	}

}
