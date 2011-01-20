package com.nas.recovery.web.action.timetrack;

import org.wc.trackrite.timetrack.TimeTrackingEntry;

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

public abstract class TimeTrackingEntryActionBase
		extends
			BaseAction<TimeTrackingEntry> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private TimeTrackingEntry timeTrackingEntry;

	@In(create = true, value = "issueAction")
	com.nas.recovery.web.action.issues.IssueAction issueAction;

	@In(create = true, value = "timeSheetAction")
	com.nas.recovery.web.action.timetrack.TimeSheetAction timeSheetAction;

	@DataModel
	private List<TimeTrackingEntry> timeTrackingEntryRecordList;

	public void setTimeTrackingEntryId(Long id) {
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
	public void setTimeTrackingEntryIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setIssueId(Long id) {

		if (id != null && id > 0)
			getInstance().setIssue(issueAction.loadFromId(id));

	}

	public Long getIssueId() {
		if (getInstance().getIssue() != null)
			return getInstance().getIssue().getId();
		return 0L;
	}

	public void setTimeSheetId(Long id) {

		if (id != null && id > 0)
			getInstance().setTimeSheet(timeSheetAction.loadFromId(id));

	}

	public Long getTimeSheetId() {
		if (getInstance().getTimeSheet() != null)
			return getInstance().getTimeSheet().getId();
		return 0L;
	}

	public Long getTimeTrackingEntryId() {
		return (Long) getId();
	}

	public TimeTrackingEntry getEntity() {
		return timeTrackingEntry;
	}

	//@Override
	public void setEntity(TimeTrackingEntry t) {
		this.timeTrackingEntry = t;
		loadAssociations();
	}

	public TimeTrackingEntry getTimeTrackingEntry() {
		return (TimeTrackingEntry) getInstance();
	}

	@Override
	protected TimeTrackingEntry createInstance() {
		return new TimeTrackingEntry();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		org.wc.trackrite.issues.Issue issue = issueAction.getDefinedInstance();
		if (issue != null && isNew()) {
			getInstance().setIssue(issue);
		}

		org.wc.trackrite.timetrack.TimeSheet timeSheet = timeSheetAction
				.getDefinedInstance();
		if (timeSheet != null && isNew()) {
			getInstance().setTimeSheet(timeSheet);
		}

	}

	public boolean isWired() {
		return true;
	}

	public TimeTrackingEntry getDefinedInstance() {
		return (TimeTrackingEntry) (isIdDefined() ? getInstance() : null);
	}

	public void setTimeTrackingEntry(TimeTrackingEntry t) {
		this.timeTrackingEntry = t;
		loadAssociations();
	}

	@Override
	public Class<TimeTrackingEntry> getEntityClass() {
		return TimeTrackingEntry.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (timeTrackingEntry.getIssue() != null) {
			criteria = criteria.add(Restrictions.eq("issue.id",
					timeTrackingEntry.getIssue().getId()));
		}

		if (timeTrackingEntry.getTimeSheet() != null) {
			criteria = criteria.add(Restrictions.eq("timeSheet.id",
					timeTrackingEntry.getTimeSheet().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (timeTrackingEntry.getIssue() != null) {
			issueAction.setInstance(getInstance().getIssue());
		}

		if (timeTrackingEntry.getTimeSheet() != null) {
			timeSheetAction.setInstance(getInstance().getTimeSheet());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
