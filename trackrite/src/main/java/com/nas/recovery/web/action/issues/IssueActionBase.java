package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Issue;

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

public abstract class IssueActionBase extends BaseAction<Issue>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Issue issue;

	@In(create = true, value = "projectAction")
	com.nas.recovery.web.action.issues.ProjectAction projectAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.domain.EmployeeAction developerAction;

	@In(create = true, value = "categoryAction")
	com.nas.recovery.web.action.issues.CategoryAction categoryAction;

	@DataModel
	private List<Issue> issueRecordList;

	public void setIssueId(Long id) {
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
	public void setIssueIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
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

	public void setDeveloperId(Long id) {

		if (id != null && id > 0)
			getInstance().setDeveloper(developerAction.loadFromId(id));

	}

	public Long getDeveloperId() {
		if (getInstance().getDeveloper() != null)
			return getInstance().getDeveloper().getId();
		return 0L;
	}

	public void setCategoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setCategory(categoryAction.loadFromId(id));

	}

	public Long getCategoryId() {
		if (getInstance().getCategory() != null)
			return getInstance().getCategory().getId();
		return 0L;
	}

	public Long getIssueId() {
		return (Long) getId();
	}

	public Issue getEntity() {
		return issue;
	}

	//@Override
	public void setEntity(Issue t) {
		this.issue = t;
		loadAssociations();
	}

	public Issue getIssue() {
		return (Issue) getInstance();
	}

	@Override
	protected Issue createInstance() {
		return new Issue();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		org.wc.trackrite.issues.Project project = projectAction
				.getDefinedInstance();
		if (project != null && isNew()) {
			getInstance().setProject(project);
		}

		org.wc.trackrite.domain.Employee developer = developerAction
				.getDefinedInstance();
		if (developer != null && isNew()) {
			getInstance().setDeveloper(developer);
		}

		org.wc.trackrite.issues.Category category = categoryAction
				.getDefinedInstance();
		if (category != null && isNew()) {
			getInstance().setCategory(category);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Issue getDefinedInstance() {
		return (Issue) (isIdDefined() ? getInstance() : null);
	}

	public void setIssue(Issue t) {
		this.issue = t;
		loadAssociations();
	}

	@Override
	public Class<Issue> getEntityClass() {
		return Issue.class;
	}

	public String downloadFile(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getFile());
		return "success";
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (issue.getProject() != null) {
			criteria = criteria.add(Restrictions.eq("project.id", issue
					.getProject().getId()));
		}

		if (issue.getDeveloper() != null) {
			criteria = criteria.add(Restrictions.eq("developer.id", issue
					.getDeveloper().getId()));
		}

		if (issue.getCategory() != null) {
			criteria = criteria.add(Restrictions.eq("category.id", issue
					.getCategory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (issue.getProject() != null) {
			projectAction.setInstance(getInstance().getProject());
		}

		if (issue.getDeveloper() != null) {
			developerAction.setInstance(getInstance().getDeveloper());
		}

		if (issue.getCategory() != null) {
			categoryAction.setInstance(getInstance().getCategory());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public void updateStatus(String status) {

	}

}
