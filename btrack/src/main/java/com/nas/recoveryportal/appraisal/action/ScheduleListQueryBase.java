package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.Schedule;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.Schedule;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ScheduleListQueryBase extends BaseQuery<Schedule, Long> {

	//private static final String EJBQL = "select schedule from Schedule schedule";

	private Schedule schedule = new Schedule();

	private static final String[] RESTRICTIONS = {
			"schedule.id = #{scheduleList.schedule.id}",

			"schedule.scheduleItem = #{scheduleList.schedule.scheduleItem}",

			"lower(schedule.name) like concat(lower(#{scheduleList.schedule.name}),'%')",

			"schedule.project = #{scheduleList.schedule.project}",

			"schedule.dateCreated <= #{scheduleList.dateCreatedRange.end}",
			"schedule.dateCreated >= #{scheduleList.dateCreatedRange.begin}",};

	public Schedule getSchedule() {
		return schedule;
	}

	@Override
	public Class<Schedule> getEntityClass() {
		return Schedule.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
