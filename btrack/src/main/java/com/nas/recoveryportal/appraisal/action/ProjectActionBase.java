package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.Project;

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

import com.nas.recoveryportal.appraisal.Story;

public class ProjectActionBase extends BaseAction<Project>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Project project;

	@In(create = true, value = "teamMemberAction")
	com.nas.recoveryportal.appraisal.action.TeamMemberAction architectAction;

	@In(create = true, value = "teamMemberAction")
	com.nas.recoveryportal.appraisal.action.TeamMemberAction managerAction;

	@DataModel
	private List<Project> projectRecordList;

	public void setProjectId(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getProjectId() {
		return (Long) getId();
	}

	@Factory("projectRecordList")
	@Observer("archivedProject")
	public void findRecords() {
		search();
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
		com.nas.recoveryportal.appraisal.TeamMember architect = architectAction
				.getDefinedInstance();
		if (architect != null) {
			getInstance().setArchitect(architect);
		}
		com.nas.recoveryportal.appraisal.TeamMember manager = managerAction
				.getDefinedInstance();
		if (manager != null) {
			getInstance().setManager(manager);
		}

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

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (project.getArchitect() != null) {
			criteria = criteria.add(Restrictions.eq("architect.id", project
					.getArchitect().getId()));
		}

		if (project.getManager() != null) {
			criteria = criteria.add(Restrictions.eq("manager.id", project
					.getManager().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (project.getArchitect() != null) {
			architectAction.setEntity(getEntity().getArchitect());
		}

		if (project.getManager() != null) {
			managerAction.setEntity(getEntity().getManager());
		}

	}

	public void updateAssociations() {

	}

	private List<Story> listStory;

	void initListStory() {
		listStory = new ArrayList<Story>();
		if (project.getStory().isEmpty()) {

		} else
			listStory.addAll(project.getStory());
	}

	public List<Story> getListStory() {
		if (listStory == null) {
			initListStory();
		}
		return listStory;
	}

	public void setListStory(List<Story> listStory) {
		this.listStory = listStory;
	}

	public void deleteStory(Story story) {
		listStory.remove(story);
	}

	@Begin(join = true)
	public void addStory() {
		Story story = new Story();

		story.setProject(project);

		listStory.add(story);
	}

	public void updateComposedAssociations() {

		project.getStory().clear();
		project.getStory().addAll(listStory);

	}

	public List<Project> getEntityList() {
		if (projectRecordList == null) {
			findRecords();
		}
		return projectRecordList;
	}

}
