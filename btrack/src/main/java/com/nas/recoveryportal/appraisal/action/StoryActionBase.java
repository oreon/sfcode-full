package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.Story;

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

import com.nas.recoveryportal.appraisal.StoryComponent;

public class StoryActionBase extends BaseAction<Story>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Story story;

	@In(create = true, value = "projectAction")
	com.nas.recoveryportal.appraisal.action.ProjectAction projectAction;

	@DataModel
	private List<Story> storyRecordList;

	public void setStoryId(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getStoryId() {
		return (Long) getId();
	}

	@Factory("storyRecordList")
	@Observer("archivedStory")
	public void findRecords() {
		search();
	}

	public Story getEntity() {
		return story;
	}

	@Override
	public void setEntity(Story t) {
		this.story = t;
		loadAssociations();
	}

	public Story getStory() {
		return getInstance();
	}

	@Override
	protected Story createInstance() {
		return new Story();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recoveryportal.appraisal.Project project = projectAction
				.getDefinedInstance();
		if (project != null) {
			getInstance().setProject(project);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Story getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setStory(Story t) {
		this.story = t;
		loadAssociations();
	}

	@Override
	public Class<Story> getEntityClass() {
		return Story.class;
	}

	@Override
	public void setEntityList(List<Story> list) {
		this.storyRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (story.getProject() != null) {
			criteria = criteria.add(Restrictions.eq("project.id", story
					.getProject().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (story.getProject() != null) {
			projectAction.setEntity(getEntity().getProject());
		}

	}

	public void updateAssociations() {

	}

	private List<StoryComponent> listStoryComponents;

	void initListStoryComponents() {
		listStoryComponents = new ArrayList<StoryComponent>();
		if (story.getStoryComponents().isEmpty()) {

			addStoryComponents();

		} else
			listStoryComponents.addAll(story.getStoryComponents());
	}

	public List<StoryComponent> getListStoryComponents() {
		if (listStoryComponents == null) {
			initListStoryComponents();
		}
		return listStoryComponents;
	}

	public void setListStoryComponents(List<StoryComponent> listStoryComponents) {
		this.listStoryComponents = listStoryComponents;
	}

	public void deleteStoryComponents(StoryComponent storyComponents) {
		listStoryComponents.remove(storyComponents);
	}

	@Begin(join = true)
	public void addStoryComponents() {
		StoryComponent storyComponents = new StoryComponent();

		storyComponents.setStory(story);

		listStoryComponents.add(storyComponents);
	}

	public void updateComposedAssociations() {

		story.getStoryComponents().clear();
		story.getStoryComponents().addAll(listStoryComponents);

	}

	public List<Story> getEntityList() {
		if (storyRecordList == null) {
			findRecords();
		}
		return storyRecordList;
	}

}
