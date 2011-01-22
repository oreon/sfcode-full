package com.nas.recovery.web.action.schedule;

import org.wc.trackrite.schedule.ScheduleItem;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.schedule.ScheduleItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ScheduleItemListQueryBase
		extends
			BaseQuery<ScheduleItem, Long> {

	private static final String EJBQL = "select scheduleItem from ScheduleItem scheduleItem";

	protected ScheduleItem scheduleItem = new ScheduleItem();

	public ScheduleItem getScheduleItem() {
		return scheduleItem;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ScheduleItem> getEntityClass() {
		return ScheduleItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

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

	@Observer("archivedScheduleItem")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ScheduleItem e) {

		builder.append("\""
				+ (e.getStartDate() != null ? e.getStartDate() : "") + "\",");

		builder.append("\"" + (e.getEndDate() != null ? e.getEndDate() : "")
				+ "\",");

		builder.append("\""
				+ (e.getEmployee() != null
						? e.getEmployee().getDisplayName()
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("StartDate" + ",");

		builder.append("EndDate" + ",");

		builder.append("Employee" + ",");

		builder.append("\r\n");
	}
}
