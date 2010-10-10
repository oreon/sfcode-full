package com.nas.recovery.web.action.timetrack;

import org.wc.trackrite.timetrack.WorkDay;

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

import org.wc.trackrite.timetrack.TimeTrackingEntry;

public abstract class WorkDayActionBase extends BaseAction<WorkDay>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private WorkDay workDay;

	@DataModel
	private List<WorkDay> workDayRecordList;

	public void setWorkDayId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	public Long getWorkDayId() {
		return (Long) getId();
	}

	//@Factory("workDayRecordList")
	//@Observer("archivedWorkDay")
	public void findRecords() {
		//search();
	}

	public WorkDay getEntity() {
		return workDay;
	}

	@Override
	public void setEntity(WorkDay t) {
		this.workDay = t;
		loadAssociations();
	}

	public WorkDay getWorkDay() {
		return getInstance();
	}

	@Override
	protected WorkDay createInstance() {
		return new WorkDay();
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

	public WorkDay getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setWorkDay(WorkDay t) {
		this.workDay = t;
		loadAssociations();
	}

	@Override
	public Class<WorkDay> getEntityClass() {
		return WorkDay.class;
	}

	@Override
	public void setEntityList(List<WorkDay> list) {
		this.workDayRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListTimeTrackingEntrys();

	}

	public void updateAssociations() {

	}

	protected List<org.wc.trackrite.timetrack.TimeTrackingEntry> listTimeTrackingEntrys;

	void initListTimeTrackingEntrys() {
		listTimeTrackingEntrys = new ArrayList<org.wc.trackrite.timetrack.TimeTrackingEntry>();

		if (getInstance().getTimeTrackingEntrys().isEmpty()) {

		} else
			listTimeTrackingEntrys
					.addAll(getInstance().getTimeTrackingEntrys());

	}

	public List<org.wc.trackrite.timetrack.TimeTrackingEntry> getListTimeTrackingEntrys() {
		if (listTimeTrackingEntrys == null)
			initListTimeTrackingEntrys();
		return listTimeTrackingEntrys;
	}

	public void setListTimeTrackingEntrys(
			List<org.wc.trackrite.timetrack.TimeTrackingEntry> listTimeTrackingEntrys) {
		this.listTimeTrackingEntrys = listTimeTrackingEntrys;
	}

	public void deleteTimeTrackingEntrys(int index) {
		listTimeTrackingEntrys.remove(index);
	}

	@Begin(join = true)
	public void addTimeTrackingEntrys() {
		TimeTrackingEntry timeTrackingEntrys = new TimeTrackingEntry();

		timeTrackingEntrys.setWorkDay(getInstance());

		getListTimeTrackingEntrys().add(timeTrackingEntrys);
	}

	public void updateComposedAssociations() {

		if (listTimeTrackingEntrys != null) {
			getInstance().getTimeTrackingEntrys().clear();
			getInstance().getTimeTrackingEntrys()
					.addAll(listTimeTrackingEntrys);
		}
	}

	public List<WorkDay> getEntityList() {
		if (workDayRecordList == null) {
			findRecords();
		}
		return workDayRecordList;
	}

}
