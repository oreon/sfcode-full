package com.nas.recovery.web.action.timetrack;

import org.wc.trackrite.timetrack.TimeTrackingEntry;

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

import org.wc.trackrite.timetrack.TimeTrackingEntry;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class TimeTrackingEntryListQueryBase
		extends
			BaseQuery<TimeTrackingEntry, Long> {

	//private static final String EJBQL = "select timeTrackingEntry from TimeTrackingEntry timeTrackingEntry";

	private TimeTrackingEntry timeTrackingEntry = new TimeTrackingEntry();

	private Range<Integer> timeTrackingEntry_hoursRange = new Range<Integer>();
	public Range<Integer> getTimeTrackingEntry_hoursRange() {
		return timeTrackingEntry_hoursRange;
	}
	public void setTimeTrackingEntry_hours(
			Range<Integer> timeTrackingEntry_hoursRange) {
		this.timeTrackingEntry_hoursRange = timeTrackingEntry_hoursRange;
	}

	private Range<Date> timeTrackingEntry_dateRange = new Range<Date>();
	public Range<Date> getTimeTrackingEntry_dateRange() {
		return timeTrackingEntry_dateRange;
	}
	public void setTimeTrackingEntry_date(
			Range<Date> timeTrackingEntry_dateRange) {
		this.timeTrackingEntry_dateRange = timeTrackingEntry_dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"timeTrackingEntry.id = #{timeTrackingEntryList.timeTrackingEntry.id}",

			"timeTrackingEntry.employee = #{timeTrackingEntryList.timeTrackingEntry.employee}",

			"timeTrackingEntry.hours >= #{timeTrackingEntryList.timeTrackingEntry_hoursRange.begin}",
			"timeTrackingEntry.hours <= #{timeTrackingEntryList.timeTrackingEntry_hoursRange.end}",

			"lower(timeTrackingEntry.details) like concat(lower(#{timeTrackingEntryList.timeTrackingEntry.details}),'%')",

			"timeTrackingEntry.date >= #{timeTrackingEntryList.timeTrackingEntry_dateRange.begin}",
			"timeTrackingEntry.date <= #{timeTrackingEntryList.timeTrackingEntry_dateRange.end}",

			"timeTrackingEntry.project = #{timeTrackingEntryList.timeTrackingEntry.project}",

			"timeTrackingEntry.dateCreated <= #{timeTrackingEntryList.dateCreatedRange.end}",
			"timeTrackingEntry.dateCreated >= #{timeTrackingEntryList.dateCreatedRange.begin}",};

	public TimeTrackingEntry getTimeTrackingEntry() {
		return timeTrackingEntry;
	}

	@Override
	public Class<TimeTrackingEntry> getEntityClass() {
		return TimeTrackingEntry.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedTimeTrackingEntry")
	public void onArchive() {
		refresh();
	}
}
