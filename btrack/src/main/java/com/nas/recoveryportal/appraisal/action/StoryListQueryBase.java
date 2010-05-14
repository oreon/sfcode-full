package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.Story;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.Story;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class StoryListQueryBase extends BaseQuery<Story, Long> {

	//private static final String EJBQL = "select story from Story story";

	private Story story = new Story();

	private static final String[] RESTRICTIONS = {
			"story.id = #{storyList.story.id}",

			"lower(story.title) like concat(lower(#{storyList.story.title}),'%')",

			"story.description = #{storyList.story.description}",

			"story.project = #{storyList.story.project}",

			"story.dateCreated <= #{storyList.dateCreatedRange.end}",
			"story.dateCreated >= #{storyList.dateCreatedRange.begin}",};

	public Story getStory() {
		return story;
	}

	@Override
	public Class<Story> getEntityClass() {
		return Story.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
