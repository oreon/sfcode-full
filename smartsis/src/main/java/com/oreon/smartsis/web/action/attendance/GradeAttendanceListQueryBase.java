package com.oreon.smartsis.web.action.attendance;

import com.oreon.smartsis.attendance.GradeAttendance;

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

import com.oreon.smartsis.attendance.GradeAttendance;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GradeAttendanceListQueryBase
		extends
			BaseQuery<GradeAttendance, Long> {

	private static final String EJBQL = "select gradeAttendance from GradeAttendance gradeAttendance";

	protected GradeAttendance gradeAttendance = new GradeAttendance();

	public GradeAttendance getGradeAttendance() {
		return gradeAttendance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<GradeAttendance> getEntityClass() {
		return GradeAttendance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateRange = new Range<Date>();
	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"gradeAttendance.id = #{gradeAttendanceList.gradeAttendance.id}",

			"gradeAttendance.date >= #{gradeAttendanceList.dateRange.begin}",
			"gradeAttendance.date <= #{gradeAttendanceList.dateRange.end}",

			"gradeAttendance.grade.id = #{gradeAttendanceList.gradeAttendance.grade.id}",

			"gradeAttendance.dateCreated <= #{gradeAttendanceList.dateCreatedRange.end}",
			"gradeAttendance.dateCreated >= #{gradeAttendanceList.dateCreatedRange.begin}",};

	@Observer("archivedGradeAttendance")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, GradeAttendance e) {

		builder.append("\"" + (e.getDate() != null ? e.getDate() : "") + "\",");

		builder.append("\""
				+ (e.getGrade() != null ? e.getGrade().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Date" + ",");

		builder.append("Grade" + ",");

		builder.append("\r\n");
	}
}
