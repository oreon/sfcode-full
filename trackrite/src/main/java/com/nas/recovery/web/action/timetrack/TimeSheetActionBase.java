package com.nas.recovery.web.action.timetrack;

import org.wc.trackrite.timetrack.TimeSheet;

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
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.timetrack.TimeTrackingEntry;

public abstract class TimeSheetActionBase extends BaseAction<TimeSheet>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private TimeSheet timeSheet;

	@DataModel
	private List<TimeSheet> timeSheetRecordList;

	public void setTimeSheetId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setTimeSheetIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getTimeSheetId() {
		return (Long) getId();
	}

	public TimeSheet getEntity() {
		return timeSheet;
	}

	//@Override
	public void setEntity(TimeSheet t) {
		this.timeSheet = t;
		loadAssociations();
	}

	public TimeSheet getTimeSheet() {
		return (TimeSheet) getInstance();
	}

	@Override
	protected TimeSheet createInstance() {
		return new TimeSheet();
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

	public TimeSheet getDefinedInstance() {
		return (TimeSheet) (isIdDefined() ? getInstance() : null);
	}

	public void setTimeSheet(TimeSheet t) {
		this.timeSheet = t;
		loadAssociations();
	}

	@Override
	public Class<TimeSheet> getEntityClass() {
		return TimeSheet.class;
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

	protected List<org.wc.trackrite.timetrack.TimeTrackingEntry> listTimeTrackingEntrys = new ArrayList<org.wc.trackrite.timetrack.TimeTrackingEntry>();

	void initListTimeTrackingEntrys() {

		if (listTimeTrackingEntrys.isEmpty())
			listTimeTrackingEntrys
					.addAll(getInstance().getTimeTrackingEntrys());

	}

	public List<org.wc.trackrite.timetrack.TimeTrackingEntry> getListTimeTrackingEntrys() {

		prePopulateListTimeTrackingEntrys();
		return listTimeTrackingEntrys;
	}

	public void prePopulateListTimeTrackingEntrys() {
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
		initListTimeTrackingEntrys();
		TimeTrackingEntry timeTrackingEntrys = new TimeTrackingEntry();

		timeTrackingEntrys.setTimeSheet(getInstance());

		getListTimeTrackingEntrys().add(timeTrackingEntrys);
	}

	public void updateComposedAssociations() {

		if (listTimeTrackingEntrys != null) {
			getInstance().getTimeTrackingEntrys().clear();
			getInstance().getTimeTrackingEntrys()
					.addAll(listTimeTrackingEntrys);
		}
	}

	public void clearLists() {
		listTimeTrackingEntrys.clear();

	}

}
