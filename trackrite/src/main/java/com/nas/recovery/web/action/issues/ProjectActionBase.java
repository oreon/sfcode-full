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
import org.jboss.seam.security.Identity;

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

	@In(create = true, value = "issueAction")
	com.nas.recovery.web.action.issues.IssueAction issuesAction;

	@DataModel
	private List<Project> projectRecordList;

	public void setProjectId(Long id) {
		if (id == 0) {
			clearInstance();
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
	public void setProjectIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getProjectId() {
		return (Long) getId();
	}

	public Project getEntity() {
		return project;
	}

	//@Override
	public void setEntity(Project t) {
		this.project = t;
		loadAssociations();
	}

	public Project getProject() {
		return (Project) getInstance();
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
		return (Project) (isIdDefined() ? getInstance() : null);
	}

	public void setProject(Project t) {
		this.project = t;
		loadAssociations();
	}

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListIssues();

		initListEmployees();
		initListAvailableEmployees();

	}

	public void updateAssociations() {

		org.wc.trackrite.issues.Issue issues = (org.wc.trackrite.issues.Issue) org.jboss.seam.Component
				.getInstance("issue");
		issues.setProject(project);
		events.raiseTransactionSuccessEvent("archivedIssue");

	}

	protected List<org.wc.trackrite.issues.Issue> listIssues = new ArrayList<org.wc.trackrite.issues.Issue>();

	void initListIssues() {

		if (listIssues.isEmpty())
			listIssues.addAll(getInstance().getIssues());

	}

	public List<org.wc.trackrite.issues.Issue> getListIssues() {

		prePopulateListIssues();
		return listIssues;
	}

	public void prePopulateListIssues() {
	}

	public void setListIssues(List<org.wc.trackrite.issues.Issue> listIssues) {
		this.listIssues = listIssues;
	}

	public void deleteIssues(int index) {
		listIssues.remove(index);
	}

	@Begin(join = true)
	public void addIssues() {
		initListIssues();
		Issue issues = new Issue();

		issues.setProject(getInstance());

		getListIssues().add(issues);
	}

	protected List<org.wc.trackrite.domain.Employee> listEmployees = new ArrayList<org.wc.trackrite.domain.Employee>();

	void initListEmployees() {

		if (listEmployees.isEmpty())
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<org.wc.trackrite.domain.Employee> getListEmployees() {

		prePopulateListEmployees();
		return listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void setListEmployees(
			List<org.wc.trackrite.domain.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	protected List<org.wc.trackrite.domain.Employee> listAvailableEmployees = new ArrayList<org.wc.trackrite.domain.Employee>();

	void initListAvailableEmployees() {

		listAvailableEmployees = getEntityManager().createQuery(
				"select r from Employee r").getResultList();
		listAvailableEmployees.removeAll(getInstance().getEmployees());

	}

	@Begin(join = true)
	public List<org.wc.trackrite.domain.Employee> getListAvailableEmployees() {

		prePopulateListAvailableEmployees();
		return listAvailableEmployees;
	}

	public void prePopulateListAvailableEmployees() {
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

	public void clearLists() {
		listIssues.clear();

		listEmployees.clear();

	}

}
