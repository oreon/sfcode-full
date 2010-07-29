package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.TenantInfo;

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

public abstract class TenantInfoActionBase extends BaseAction<TenantInfo>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private TenantInfo tenantInfo;

	@DataModel
	private List<TenantInfo> tenantInfoRecordList;

	public void setTenantInfoId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getTenantInfoId() {
		return (Long) getId();
	}

	//@Factory("tenantInfoRecordList")
	//@Observer("archivedTenantInfo")
	public void findRecords() {
		//search();
	}

	public TenantInfo getEntity() {
		return tenantInfo;
	}

	@Override
	public void setEntity(TenantInfo t) {
		this.tenantInfo = t;
		loadAssociations();
	}

	public TenantInfo getTenantInfo() {
		return getInstance();
	}

	@Override
	protected TenantInfo createInstance() {
		return new TenantInfo();
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

	public TenantInfo getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setTenantInfo(TenantInfo t) {
		this.tenantInfo = t;
		loadAssociations();
	}

	@Override
	public Class<TenantInfo> getEntityClass() {
		return TenantInfo.class;
	}

	@Override
	public void setEntityList(List<TenantInfo> list) {
		this.tenantInfoRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<TenantInfo> getEntityList() {
		if (tenantInfoRecordList == null) {
			findRecords();
		}
		return tenantInfoRecordList;
	}

}
