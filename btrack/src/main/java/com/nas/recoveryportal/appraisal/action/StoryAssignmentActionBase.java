package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.StoryAssignment;

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

public class StoryAssignmentActionBase extends BaseAction<StoryAssignment>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private StoryAssignment storyAssignment;

	@In(create = true, value = "teamMemberAction")
	com.nas.recoveryportal.appraisal.action.TeamMemberAction teamMemberAction;

	@In(create = true, value = "storyAction")
	com.nas.recoveryportal.appraisal.action.StoryAction storyAction;

	@DataModel
	private List<StoryAssignment> storyAssignmentRecordList;

	public void setStoryAssignmentId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getStoryAssignmentId() {
		return (Long) getId();
	}

	@Factory("storyAssignmentRecordList")
	@Observer("archivedStoryAssignment")
	public void findRecords() {
		search();
	}

	public StoryAssignment getEntity() {
		return storyAssignment;
	}

	@Override
	public void setEntity(StoryAssignment t) {
		this.storyAssignment = t;
		loadAssociations();
	}

	public StoryAssignment getStoryAssignment() {
		return getInstance();
	}

	@Override
	protected StoryAssignment createInstance() {
		return new StoryAssignment();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recoveryportal.appraisal.TeamMember teamMember = teamMemberAction
				.getDefinedInstance();
		if (teamMember != null) {
			getInstance().setTeamMember(teamMember);
		}
		com.nas.recoveryportal.appraisal.Story story = storyAction
				.getDefinedInstance();
		if (story != null) {
			getInstance().setStory(story);
		}

	}

	public boolean isWired() {
		return true;
	}

	public StoryAssignment getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setStoryAssignment(StoryAssignment t) {
		this.storyAssignment = t;
		loadAssociations();
	}

	@Override
	public Class<StoryAssignment> getEntityClass() {
		return StoryAssignment.class;
	}

	@Override
	public void setEntityList(List<StoryAssignment> list) {
		this.storyAssignmentRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (storyAssignment.getTeamMember() != null) {
			criteria = criteria.add(Restrictions.eq("teamMember.id",
					storyAssignment.getTeamMember().getId()));
		}

		if (storyAssignment.getStory() != null) {
			criteria = criteria.add(Restrictions.eq("story.id", storyAssignment
					.getStory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (storyAssignment.getTeamMember() != null) {
			teamMemberAction.setEntity(getEntity().getTeamMember());
		}

		if (storyAssignment.getStory() != null) {
			storyAction.setEntity(getEntity().getStory());
		}

	}

	public void updateAssociations() {

	}

	public List<StoryAssignment> getEntityList() {
		if (storyAssignmentRecordList == null) {
			findRecords();
		}
		return storyAssignmentRecordList;
	}

}
