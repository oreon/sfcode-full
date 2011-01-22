package com.nas.recovery.web.action.timetrack;

import org.wc.trackrite.timetrack.TimeTrackingEntry;

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

import org.wc.trackrite.timetrack.TimeTrackingEntry;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TimeTrackingEntryListQueryBase
		extends
			BaseQuery<TimeTrackingEntry, Long> {

	private static final String EJBQL = "select timeTrackingEntry from TimeTrackingEntry timeTrackingEntry";

	protected TimeTrackingEntry timeTrackingEntry = new TimeTrackingEntry();

	public TimeTrackingEntry getTimeTrackingEntry() {
		return timeTrackingEntry;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<TimeTrackingEntry> getEntityClass() {
		return TimeTrackingEntry.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> hoursRange = new Range<Integer>();
	public Range<Integer> getHoursRange() {
		return hoursRange;
	}
	public void setHours(Range<Integer> hoursRange) {
		this.hoursRange = hoursRange;
	}

	private Range<Date> dateRange = new Range<Date>();
	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"timeTrackingEntry.id = #{timeTrackingEntryList.timeTrackingEntry.id}",

			"timeTrackingEntry.hours >= #{timeTrackingEntryList.hoursRange.begin}",
			"timeTrackingEntry.hours <= #{timeTrackingEntryList.hoursRange.end}",

			"lower(timeTrackingEntry.details) like concat(lower(#{timeTrackingEntryList.timeTrackingEntry.details}),'%')",

			"timeTrackingEntry.issue.id = #{timeTrackingEntryList.timeTrackingEntry.issue.id}",

			"timeTrackingEntry.timeSheet.id = #{timeTrackingEntryList.timeTrackingEntry.timeSheet.id}",

			"timeTrackingEntry.date >= #{timeTrackingEntryList.dateRange.begin}",
			"timeTrackingEntry.date <= #{timeTrackingEntryList.dateRange.end}",

			"timeTrackingEntry.dateCreated <= #{timeTrackingEntryList.dateCreatedRange.end}",
			"timeTrackingEntry.dateCreated >= #{timeTrackingEntryList.dateCreatedRange.begin}",};

	public List<TimeTrackingEntry> getTimeTrackingEntrysByTimeSheet(
			org.wc.trackrite.timetrack.TimeSheet timeSheet) {
		//setMaxResults(10000);
		timeTrackingEntry.setTimeSheet(timeSheet);
		return getResultList();
	}

	@Observer("archivedTimeTrackingEntry")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, TimeTrackingEntry e) {

		builder.append("\"" + (e.getHours() != null ? e.getHours() : "")
				+ "\",");

		builder.append("\"" + (e.getDetails() != null ? e.getDetails() : "")
				+ "\",");

		builder.append("\""
				+ (e.getIssue() != null ? e.getIssue().getDisplayName() : "")
				+ "\",");

		builder.append("\""
				+ (e.getTimeSheet() != null
						? e.getTimeSheet().getDisplayName()
						: "") + "\",");

		builder.append("\"" + (e.getDate() != null ? e.getDate() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Hours" + ",");

		builder.append("Details" + ",");

		builder.append("Issue" + ",");

		builder.append("TimeSheet" + ",");

		builder.append("Date" + ",");

		builder.append("\r\n");
	}
}
