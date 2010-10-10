package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Project;

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

import org.wc.trackrite.issues.Issue;

public abstract class ProjectActionBase extends BaseAction<Project>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Project project;

	@In(create = true, value = "issueList")
	com.nas.recovery.web.action.issues.IssueListQuery issueList;

	@DataModel
	private List<Project> projectRecordList;

	public void setProjectId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	public Long getProjectId() {
		return (Long) getId();
	}

	//@Factory("projectRecordList")
	//@Observer("archivedProject")
	public void findRecords() {
		//search();
	}

	public Project getEntity() {
		return project;
	}

	@Override
	public void setEntity(Project t) {
		this.project = t;
		loadAssociations();
	}

	public Project getProject() {
		return getInstance();
	}

	@Override
	protected Project createInstance() {
		return new Project();
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

	public Project getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setProject(Project t) {
		this.project = t;
		loadAssociations();
	}

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public void setEntityList(List<Project> list) {
		this.projectRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListIssues();

		try {

			issueList.getIssue().setProject(getInstance());

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}

	}

	public void updateAssociations() {

		org.wc.trackrite.issues.Issue issue = (org.wc.trackrite.issues.Issue) org.jboss.seam.Component
				.getInstance("issue");
		issue.setProject(project);
		events.raiseTransactionSuccessEvent("archivedIssue");

	}

	protected List<org.wc.trackrite.issues.Issue> listIssues;

	void initListIssues() {
		listIssues = new ArrayList<org.wc.trackrite.issues.Issue>();

		if (getInstance().getIssues().isEmpty()) {

		} else
			listIssues.addAll(getInstance().getIssues());

	}

	public List<org.wc.trackrite.issues.Issue> getListIssues() {
		if (listIssues == null)
			initListIssues();
		return listIssues;
	}

	public void setListIssues(List<org.wc.trackrite.issues.Issue> listIssues) {
		this.listIssues = listIssues;
	}

	public void deleteIssues(int index) {
		listIssues.remove(index);
	}

	@Begin(join = true)
	public void addIssues() {
		Issue issues = new Issue();

		issues.setProject(getInstance());

		getListIssues().add(issues);
	}

	protected List<org.wc.trackrite.domain.Employee> listEmployees;

	void initListEmployees() {
		listEmployees = new ArrayList<org.wc.trackrite.domain.Employee>();

		if (getInstance().getEmployees().isEmpty()) {

		} else
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<org.wc.trackrite.domain.Employee> getListEmployees() {
		if (listEmployees == null)
			initListEmployees();
		return listEmployees;
	}

	public void setListEmployees(
			List<org.wc.trackrite.domain.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	protected List<org.wc.trackrite.domain.Employee> listAvailableEmployees;

	void initListAvailableEmployees() {
		listAvailableEmployees = new ArrayList<org.wc.trackrite.domain.Employee>();

		listAvailableEmployees = getEntityManager().createQuery(
				"select r from Employee r").getResultList();
		listAvailableEmployees.removeAll(getInstance().getEmployees());

	}

	public List<org.wc.trackrite.domain.Employee> getListAvailableEmployees() {
		if (listAvailableEmployees == null)
			initListAvailableEmployees();
		return listAvailableEmployees;
	}

	public void setListAvailableEmployees(
			List<org.wc.trackrite.domain.Employee> listAvailableEmployees) {
		this.listAvailableEmployees = listAvailableEmployees;
	}

	public void updateComposedAssociations() {

		if (listIssues != null) {
			getInstance().getIssues().clear();
			getInstance().getIssues().addAll(listIssues);
		}

		if (listEmployees != null) {
			getInstance().getEmployees().clear();
			getInstance().getEmployees().addAll(listEmployees);
		}
	}

	public List<Project> getEntityList() {
		if (projectRecordList == null) {
			findRecords();
		}
		return projectRecordList;
	}

}
