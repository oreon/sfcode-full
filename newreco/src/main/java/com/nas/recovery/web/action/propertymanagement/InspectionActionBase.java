package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.Inspection;

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

public abstract class InspectionActionBase extends BaseAction<Inspection>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Inspection inspection;

	@In(create = true, value = "propertyManagerAction")
	com.nas.recovery.web.action.propertymanagement.PropertyManagerAction propertyManagerAction;

	@DataModel
	private List<Inspection> inspectionRecordList;

	public void setInspectionId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setPropertyManagerId(Long id) {
		if (id != null && id > 0)
			getInstance().setPropertyManager(
					propertyManagerAction.loadFromId(id));
	}

	public Long getPropertyManagerId() {
		if (getInstance().getPropertyManager() != null)
			return getInstance().getPropertyManager().getId();
		return 0L;
	}

	public Long getInspectionId() {
		return (Long) getId();
	}

	//@Factory("inspectionRecordList")
	//@Observer("archivedInspection")
	public void findRecords() {
		//search();
	}

	public Inspection getEntity() {
		return inspection;
	}

	@Override
	public void setEntity(Inspection t) {
		this.inspection = t;
		loadAssociations();
	}

	public Inspection getInspection() {
		return getInstance();
	}

	@Override
	protected Inspection createInstance() {
		return new Inspection();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.propertymanagement.PropertyManager propertyManager = propertyManagerAction
				.getDefinedInstance();
		if (propertyManager != null) {
			getInstance().setPropertyManager(propertyManager);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Inspection getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setInspection(Inspection t) {
		this.inspection = t;
		loadAssociations();
	}

	@Override
	public Class<Inspection> getEntityClass() {
		return Inspection.class;
	}

	@Override
	public void setEntityList(List<Inspection> list) {
		this.inspectionRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (inspection.getPropertyManager() != null) {
			criteria = criteria.add(Restrictions.eq("propertyManager.id",
					inspection.getPropertyManager().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (inspection.getPropertyManager() != null) {
			propertyManagerAction.setEntity(getEntity().getPropertyManager());
		}

	}

	public void updateAssociations() {

	}

	public List<Inspection> getEntityList() {
		if (inspectionRecordList == null) {
			findRecords();
		}
		return inspectionRecordList;
	}

}
