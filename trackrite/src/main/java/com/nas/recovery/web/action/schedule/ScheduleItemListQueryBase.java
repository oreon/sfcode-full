package com.nas.recovery.web.action.schedule;

import org.wc.trackrite.schedule.ScheduleItem;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.schedule.ScheduleItem;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ScheduleItemListQueryBase
		extends
			BaseQuery<ScheduleItem, Long> {

	//private static final String EJBQL = "select scheduleItem from ScheduleItem scheduleItem";

	protected ScheduleItem scheduleItem = new ScheduleItem();

	private Range<Date> startDateRange = new Range<Date>();
	public Range<Date> getStartDateRange() {
		return startDateRange;
	}
	public void setStartDate(Range<Date> startDateRange) {
		this.startDateRange = startDateRange;
	}

	private Range<Date> endDateRange = new Range<Date>();
	public Range<Date> getEndDateRange() {
		return endDateRange;
	}
	public void setEndDate(Range<Date> endDateRange) {
		this.endDateRange = endDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"scheduleItem.id = #{scheduleItemList.scheduleItem.id}",

			"scheduleItem.startDate >= #{scheduleItemList.startDateRange.begin}",
			"scheduleItem.startDate <= #{scheduleItemList.startDateRange.end}",

			"scheduleItem.endDate >= #{scheduleItemList.endDateRange.begin}",
			"scheduleItem.endDate <= #{scheduleItemList.endDateRange.end}",

			"scheduleItem.employee.id = #{scheduleItemList.scheduleItem.employee.id}",

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

	@Observer("archivedScheduleItem")
	public void onArchive() {
		refresh();
	}
}
