package com.nas.recovery.web.action.schedule;

import org.wc.trackrite.schedule.Schedule;

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

import org.wc.trackrite.schedule.Schedule;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ScheduleListQueryBase extends BaseQuery<Schedule, Long> {

	private static final String EJBQL = "select schedule from Schedule schedule";

	protected Schedule schedule = new Schedule();

	public Schedule getSchedule() {
		return schedule;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Schedule> getEntityClass() {
		return Schedule.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"schedule.id = #{scheduleList.schedule.id}",

			"schedule.project.id = #{scheduleList.schedule.project.id}",

			"lower(schedule.name) like concat(lower(#{scheduleList.schedule.name}),'%')",

			"schedule.dateCreated <= #{scheduleList.dateCreatedRange.end}",
			"schedule.dateCreated >= #{scheduleList.dateCreatedRange.begin}",};

	@Observer("archivedSchedule")
	public void onArchive() {
		refresh();
	}

}
