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

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.domain.EmployeeAction employeeAction;

	@In(create = true, value = "projectAction")
	com.nas.recovery.web.action.issues.ProjectAction projectAction;

	@DataModel
	private List<TimeTrackingEntry> timeTrackingEntryRecordList;

	public void setTimeTrackingEntryId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setEmployeeId(Long id) {
		if (id != null && id > 0)
			getInstance().setEmployee(employeeAction.loadFromId(id));
	}

	public Long getEmployeeId() {
		if (getInstance().getEmployee() != null)
			return getInstance().getEmployee().getId();
		return 0L;
	}
	public void setProjectId(Long id) {
		if (id != null && id > 0)
			getInstance().setProject(projectAction.loadFromId(id));
	}

	public Long getProjectId() {
		if (getInstance().getProject() != null)
			return getInstance().getProject().getId();
		return 0L;
	}

	public Long getTimeTrackingEntryId() {
		return (Long) getId();
	}

	//@Factory("timeTrackingEntryRecordList")
	//@Observer("archivedTimeTrackingEntry")
	public void findRecords() {
		//search();
	}

	public TimeTrackingEntry getEntity() {
		return timeTrackingEntry;
	}

	@Override
	public void setEntity(TimeTrackingEntry t) {
		this.timeTrackingEntry = t;
		loadAssociations();
	}

	public TimeTrackingEntry getTimeTrackingEntry() {
		return getInstance();
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
		org.wc.trackrite.domain.Employee employee = employeeAction
				.getDefinedInstance();
		if (employee != null) {
			getInstance().setEmployee(employee);
		}
		org.wc.trackrite.issues.Project project = projectAction
				.getDefinedInstance();
		if (project != null) {
			getInstance().setProject(project);
		}

	}

	public boolean isWired() {
		return true;
	}

	public TimeTrackingEntry getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setTimeTrackingEntry(TimeTrackingEntry t) {
		this.timeTrackingEntry = t;
		loadAssociations();
	}

	@Override
	public Class<TimeTrackingEntry> getEntityClass() {
		return TimeTrackingEntry.class;
	}

	@Override
	public void setEntityList(List<TimeTrackingEntry> list) {
		this.timeTrackingEntryRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (timeTrackingEntry.getEmployee() != null) {
			criteria = criteria.add(Restrictions.eq("employee.id",
					timeTrackingEntry.getEmployee().getId()));
		}

		if (timeTrackingEntry.getProject() != null) {
			criteria = criteria.add(Restrictions.eq("project.id",
					timeTrackingEntry.getProject().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (timeTrackingEntry.getEmployee() != null) {
			employeeAction.setInstance(getInstance().getEmployee());
		}

		if (timeTrackingEntry.getProject() != null) {
			projectAction.setInstance(getInstance().getProject());
		}

	}

	public void updateAssociations() {

	}

	public List<TimeTrackingEntry> getEntityList() {
		if (timeTrackingEntryRecordList == null) {
			findRecords();
		}
		return timeTrackingEntryRecordList;
	}

}
