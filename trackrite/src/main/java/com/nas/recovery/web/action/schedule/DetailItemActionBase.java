package com.nas.recovery.web.action.schedule;

import org.wc.trackrite.schedule.DetailItem;

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

public abstract class DetailItemActionBase extends BaseAction<DetailItem>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DetailItem detailItem;

	@In(create = true, value = "scheduleItemAction")
	com.nas.recovery.web.action.schedule.ScheduleItemAction scheduleItemAction;

	@DataModel
	private List<DetailItem> detailItemRecordList;

	public void setDetailItemId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	public void setScheduleItemId(Long id) {
		if (id != null && id > 0)
			getInstance().setScheduleItem(scheduleItemAction.loadFromId(id));
	}

	public Long getScheduleItemId() {
		if (getInstance().getScheduleItem() != null)
			return getInstance().getScheduleItem().getId();
		return 0L;
	}

	public Long getDetailItemId() {
		return (Long) getId();
	}

	//@Factory("detailItemRecordList")
	//@Observer("archivedDetailItem")
	public void findRecords() {
		//search();
	}

	public DetailItem getEntity() {
		return detailItem;
	}

	

	public DetailItem getDetailItem() {
		return getInstance();
	}

	@Override
	protected DetailItem createInstance() {
		return new DetailItem();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		org.wc.trackrite.schedule.ScheduleItem scheduleItem = scheduleItemAction
				.getDefinedInstance();
		if (scheduleItem != null) {
			getInstance().setScheduleItem(scheduleItem);
		}

	}

	public boolean isWired() {
		return true;
	}

	public DetailItem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setDetailItem(DetailItem t) {
		this.detailItem = t;
		loadAssociations();
	}

	@Override
	public Class<DetailItem> getEntityClass() {
		return DetailItem.class;
	}

	

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (detailItem.getScheduleItem() != null) {
			criteria = criteria.add(Restrictions.eq("scheduleItem.id",
					detailItem.getScheduleItem().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (detailItem.getScheduleItem() != null) {
			scheduleItemAction.setInstance(getInstance().getScheduleItem());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<DetailItem> getEntityList() {
		if (detailItemRecordList == null) {
			findRecords();
		}
		return detailItemRecordList;
	}

}
