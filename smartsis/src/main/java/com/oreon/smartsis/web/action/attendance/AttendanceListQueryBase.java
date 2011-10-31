package com.oreon.smartsis.web.action.attendance;

import com.oreon.smartsis.attendance.Attendance;

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

import com.oreon.smartsis.attendance.Attendance;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AttendanceListQueryBase
		extends
			BaseQuery<Attendance, Long> {

	private static final String EJBQL = "select attendance from Attendance attendance";

	protected Attendance attendance = new Attendance();

	public Attendance getAttendance() {
		return attendance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Attendance> getEntityClass() {
		return Attendance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"attendance.id = #{attendanceList.attendance.id}",

			"attendance.student.id = #{attendanceList.attendance.student.id}",

			"attendance.gradeAttendance.id = #{attendanceList.attendance.gradeAttendance.id}",

			"attendance.absenceCode = #{attendanceList.attendance.absenceCode}",

			"attendance.dateCreated <= #{attendanceList.dateCreatedRange.end}",
			"attendance.dateCreated >= #{attendanceList.dateCreatedRange.begin}",};

	public List<Attendance> getAttendancesByGradeAttendance(
			com.oreon.smartsis.attendance.GradeAttendance gradeAttendance) {
		//setMaxResults(10000);
		attendance.setGradeAttendance(gradeAttendance);
		return getResultList();
	}

	@Observer("archivedAttendance")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Attendance e) {

		builder.append("\""
				+ (e.getStudent() != null ? e.getStudent().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getGradeAttendance() != null ? e.getGradeAttendance()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAbsenceCode() != null ? e.getAbsenceCode() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Student" + ",");

		builder.append("GradeAttendance" + ",");

		builder.append("AbsenceCode" + ",");

		builder.append("\r\n");
	}
}
