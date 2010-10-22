package com.nas.recovery.web.action.schedule;

import org.wc.trackrite.schedule.ScheduleItem;

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

public abstract class ScheduleItemActionBase extends BaseAction<ScheduleItem>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ScheduleItem scheduleItem;

	@In(create = true, value = "detailItemAction")
	com.nas.recovery.web.action.schedule.DetailItemAction detailItemAction;

	@DataModel
	private List<ScheduleItem> scheduleItemRecordList;

	public void setScheduleItemId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	public void setDetailItemId(Long id) {
		if (id != null && id > 0)
			getInstance().setDetailItem(detailItemAction.loadFromId(id));
	}

	public Long getDetailItemId() {
		if (getInstance().getDetailItem() != null)
			return getInstance().getDetailItem().getId();
		return 0L;
	}

	public Long getScheduleItemId() {
		return (Long) getId();
	}

	//@Factory("scheduleItemRecordList")
	//@Observer("archivedScheduleItem")
	public void findRecords() {
		//search();
	}

	public ScheduleItem getEntity() {
		return scheduleItem;
	}

	@Override
	public void setEntity(ScheduleItem t) {
		this.scheduleItem = t;
		loadAssociations();
	}

	public ScheduleItem getScheduleItem() {
		return getInstance();
	}

	@Override
	protected ScheduleItem createInstance() {
		return new ScheduleItem();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		org.wc.trackrite.schedule.DetailItem detailItem = detailItemAction
				.getDefinedInstance();
		if (detailItem != null) {
			getInstance().setDetailItem(detailItem);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ScheduleItem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setScheduleItem(ScheduleItem t) {
		this.scheduleItem = t;
		loadAssociations();
	}

	@Override
	public Class<ScheduleItem> getEntityClass() {
		return ScheduleItem.class;
	}

	@Override
	public void setEntityList(List<ScheduleItem> list) {
		this.scheduleItemRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (scheduleItem.getDetailItem() != null) {
			criteria = criteria.add(Restrictions.eq("detailItem.id",
					scheduleItem.getDetailItem().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (scheduleItem.getDetailItem() != null) {
			detailItemAction.setInstance(getInstance().getDetailItem());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<ScheduleItem> getEntityList() {
		if (scheduleItemRecordList == null) {
			findRecords();
		}
		return scheduleItemRecordList;
	}

}
