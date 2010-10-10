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

	protected TimeTrackingEntry timeTrackingEntry = new TimeTrackingEntry();

	private Range<Integer> hoursRange = new Range<Integer>();
	public Range<Integer> getHoursRange() {
		return hoursRange;
	}
	public void setHours(Range<Integer> hoursRange) {
		this.hoursRange = hoursRange;
	}

	private static final String[] RESTRICTIONS = {
			"timeTrackingEntry.id = #{timeTrackingEntryList.timeTrackingEntry.id}",

			"timeTrackingEntry.hours >= #{timeTrackingEntryList.hoursRange.begin}",
			"timeTrackingEntry.hours <= #{timeTrackingEntryList.hoursRange.end}",

			"lower(timeTrackingEntry.details) like concat(lower(#{timeTrackingEntryList.timeTrackingEntry.details}),'%')",

			"timeTrackingEntry.issue.id = #{timeTrackingEntryList.timeTrackingEntry.issue.id}",

			"timeTrackingEntry.workDay.id = #{timeTrackingEntryList.timeTrackingEntry.workDay.id}",

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
