package com.nas.recovery.web.action.timetrack;

import org.wc.trackrite.timetrack.TimeSheet;

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

import org.wc.trackrite.timetrack.TimeSheet;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class TimeSheetListQueryBase extends BaseQuery<TimeSheet, Long> {

	private static final String EJBQL = "select timeSheet from TimeSheet timeSheet";

	protected TimeSheet timeSheet = new TimeSheet();

	public TimeSheet getTimeSheet() {
		return timeSheet;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<TimeSheet> getEntityClass() {
		return TimeSheet.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"timeSheet.id = #{timeSheetList.timeSheet.id}",

			"lower(timeSheet.title) like concat(lower(#{timeSheetList.timeSheet.title}),'%')",

			"timeSheet.dateCreated <= #{timeSheetList.dateCreatedRange.end}",
			"timeSheet.dateCreated >= #{timeSheetList.dateCreatedRange.begin}",};

	@Observer("archivedTimeSheet")
	public void onArchive() {
		refresh();
	}

}
