package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.StoryAssignment;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.StoryAssignment;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class StoryAssignmentListQueryBase
		extends
			BaseQuery<StoryAssignment, Long> {

	//private static final String EJBQL = "select storyAssignment from StoryAssignment storyAssignment";

	private StoryAssignment storyAssignment = new StoryAssignment();

	private static final String[] RESTRICTIONS = {
			"storyAssignment.id = #{storyAssignmentList.storyAssignment.id}",

			"storyAssignment.teamMember = #{storyAssignmentList.storyAssignment.teamMember}",

			"storyAssignment.story = #{storyAssignmentList.storyAssignment.story}",

			"storyAssignment.dateCreated <= #{storyAssignmentList.dateCreatedRange.end}",
			"storyAssignment.dateCreated >= #{storyAssignmentList.dateCreatedRange.begin}",};

	public StoryAssignment getStoryAssignment() {
		return storyAssignment;
	}

	@Override
	public Class<StoryAssignment> getEntityClass() {
		return StoryAssignment.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
