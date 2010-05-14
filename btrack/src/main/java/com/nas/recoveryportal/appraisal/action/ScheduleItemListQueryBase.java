package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.ScheduleItem;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.ScheduleItem;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ScheduleItemListQueryBase
		extends
			BaseQuery<ScheduleItem, Long> {

	//private static final String EJBQL = "select scheduleItem from ScheduleItem scheduleItem";

	private ScheduleItem scheduleItem = new ScheduleItem();

	private Range<Date> scheduleItem_beginDateRange = new Range<Date>();
	public Range<Date> getScheduleItem_beginDateRange() {
		return scheduleItem_beginDateRange;
	}
	public void setScheduleItem_beginDate(
			Range<Date> scheduleItem_beginDateRange) {
		this.scheduleItem_beginDateRange = scheduleItem_beginDateRange;
	}

	private Range<Date> scheduleItem_endDateRange = new Range<Date>();
	public Range<Date> getScheduleItem_endDateRange() {
		return scheduleItem_endDateRange;
	}
	public void setScheduleItem_endDate(Range<Date> scheduleItem_endDateRange) {
		this.scheduleItem_endDateRange = scheduleItem_endDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"scheduleItem.id = #{scheduleItemList.scheduleItem.id}",

			"scheduleItem.beginDate >= #{scheduleItemList.scheduleItem_beginDateRange.begin}",
			"scheduleItem.beginDate <= #{scheduleItemList.scheduleItem_beginDateRange.end}",

			"scheduleItem.endDate >= #{scheduleItemList.scheduleItem_endDateRange.begin}",
			"scheduleItem.endDate <= #{scheduleItemList.scheduleItem_endDateRange.end}",

			"scheduleItem.schedule = #{scheduleItemList.scheduleItem.schedule}",

			"scheduleItem.story = #{scheduleItemList.scheduleItem.story}",

			"scheduleItem.dateCreated <= #{scheduleItemList.dateCreatedRange.end}",
			"scheduleItem.dateCreated >= #{scheduleItemList.dateCreatedRange.begin}",};

	public ScheduleItem getScheduleItem() {
		return scheduleItem;
	}

	@Override
	public Class<ScheduleItem> getEntityClass() {
		return ScheduleItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
