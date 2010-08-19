package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.MortgageeInformation;

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

public abstract class MortgageeInformationActionBase
		extends
			BaseAction<MortgageeInformation> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MortgageeInformation mortgageeInformation;

	@DataModel
	private List<MortgageeInformation> mortgageeInformationRecordList;

	public void setMortgageeInformationId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getMortgageeInformationId() {
		return (Long) getId();
	}

	//@Factory("mortgageeInformationRecordList")
	//@Observer("archivedMortgageeInformation")
	public void findRecords() {
		//search();
	}

	public MortgageeInformation getEntity() {
		return mortgageeInformation;
	}

	@Override
	public void setEntity(MortgageeInformation t) {
		this.mortgageeInformation = t;
		loadAssociations();
	}

	public MortgageeInformation getMortgageeInformation() {
		return getInstance();
	}

	@Override
	protected MortgageeInformation createInstance() {
		return new MortgageeInformation();
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

	public MortgageeInformation getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setMortgageeInformation(MortgageeInformation t) {
		this.mortgageeInformation = t;
		loadAssociations();
	}

	@Override
	public Class<MortgageeInformation> getEntityClass() {
		return MortgageeInformation.class;
	}

	@Override
	public void setEntityList(List<MortgageeInformation> list) {
		this.mortgageeInformationRecordList = list;
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

	public List<MortgageeInformation> getEntityList() {
		if (mortgageeInformationRecordList == null) {
			findRecords();
		}
		return mortgageeInformationRecordList;
	}

}
