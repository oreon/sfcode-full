package com.nas.recovery.web.action.timetrack;

import org.wc.trackrite.timetrack.WorkDay;

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

import org.wc.trackrite.timetrack.WorkDay;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class WorkDayListQueryBase extends BaseQuery<WorkDay, Long> {

	//private static final String EJBQL = "select workDay from WorkDay workDay";

	protected WorkDay workDay = new WorkDay();

	private Range<Date> dateRange = new Range<Date>();
	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"workDay.id = #{workDayList.workDay.id}",

			"workDay.date >= #{workDayList.dateRange.begin}",
			"workDay.date <= #{workDayList.dateRange.end}",

			"workDay.dateCreated <= #{workDayList.dateCreatedRange.end}",
			"workDay.dateCreated >= #{workDayList.dateCreatedRange.begin}",};

	public WorkDay getWorkDay() {
		return workDay;
	}

	@Override
	public Class<WorkDay> getEntityClass() {
		return WorkDay.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedWorkDay")
	public void onArchive() {
		refresh();
	}
}
