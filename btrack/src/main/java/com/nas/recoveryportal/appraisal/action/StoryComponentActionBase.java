package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.StoryComponent;

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

public class StoryComponentActionBase extends BaseAction<StoryComponent>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private StoryComponent storyComponent;

	@In(create = true, value = "storyAction")
	com.nas.recoveryportal.appraisal.action.StoryAction storyAction;

	@DataModel
	private List<StoryComponent> storyComponentRecordList;

	public void setStoryComponentId(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getStoryComponentId() {
		return (Long) getId();
	}

	@Factory("storyComponentRecordList")
	@Observer("archivedStoryComponent")
	public void findRecords() {
		search();
	}

	public StoryComponent getEntity() {
		return storyComponent;
	}

	@Override
	public void setEntity(StoryComponent t) {
		this.storyComponent = t;
		loadAssociations();
	}

	public StoryComponent getStoryComponent() {
		return getInstance();
	}

	@Override
	protected StoryComponent createInstance() {
		return new StoryComponent();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recoveryportal.appraisal.Story story = storyAction
				.getDefinedInstance();
		if (story != null) {
			getInstance().setStory(story);
		}

	}

	public boolean isWired() {
		return true;
	}

	public StoryComponent getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setStoryComponent(StoryComponent t) {
		this.storyComponent = t;
		loadAssociations();
	}

	@Override
	public Class<StoryComponent> getEntityClass() {
		return StoryComponent.class;
	}

	@Override
	public void setEntityList(List<StoryComponent> list) {
		this.storyComponentRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (storyComponent.getStory() != null) {
			criteria = criteria.add(Restrictions.eq("story.id", storyComponent
					.getStory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (storyComponent.getStory() != null) {
			storyAction.setEntity(getEntity().getStory());
		}

	}

	public void updateAssociations() {

	}

	public List<StoryComponent> getEntityList() {
		if (storyComponentRecordList == null) {
			findRecords();
		}
		return storyComponentRecordList;
	}

}
