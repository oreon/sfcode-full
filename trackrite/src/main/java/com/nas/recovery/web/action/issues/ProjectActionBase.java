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

	public List<Project> getEntityList() {
		if (projectRecordList == null) {
			findRecords();
		}
		return projectRecordList;
	}

}
