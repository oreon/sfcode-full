package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.StoryComponent;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.StoryComponent;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class StoryComponentListQueryBase
		extends
			BaseQuery<StoryComponent, Long> {

	//private static final String EJBQL = "select storyComponent from StoryComponent storyComponent";

	private StoryComponent storyComponent = new StoryComponent();

	private Range<Integer> storyComponent_hoursRange = new Range<Integer>();
	public Range<Integer> getStoryComponent_hoursRange() {
		return storyComponent_hoursRange;
	}
	public void setStoryComponent_hours(Range<Integer> storyComponent_hoursRange) {
		this.storyComponent_hoursRange = storyComponent_hoursRange;
	}

	private static final String[] RESTRICTIONS = {
			"storyComponent.id = #{storyComponentList.storyComponent.id}",

			"lower(storyComponent.title) like concat(lower(#{storyComponentList.storyComponent.title}),'%')",

			"storyComponent.hours >= #{storyComponentList.storyComponent_hoursRange.begin}",
			"storyComponent.hours <= #{storyComponentList.storyComponent_hoursRange.end}",

			"storyComponent.story = #{storyComponentList.storyComponent.story}",

			"storyComponent.dateCreated <= #{storyComponentList.dateCreatedRange.end}",
			"storyComponent.dateCreated >= #{storyComponentList.dateCreatedRange.begin}",};

	public StoryComponent getStoryComponent() {
		return storyComponent;
	}

	@Override
	public Class<StoryComponent> getEntityClass() {
		return StoryComponent.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
