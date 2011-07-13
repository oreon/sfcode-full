package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.StudentVitalInfo;

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

import com.oreon.smartsis.domain.StudentVitalInfo;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class StudentVitalInfoListQueryBase
		extends
			BaseQuery<StudentVitalInfo, Long> {

	private static final String EJBQL = "select studentVitalInfo from StudentVitalInfo studentVitalInfo";

	protected StudentVitalInfo studentVitalInfo = new StudentVitalInfo();

	public StudentVitalInfo getStudentVitalInfo() {
		return studentVitalInfo;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<StudentVitalInfo> getEntityClass() {
		return StudentVitalInfo.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"studentVitalInfo.id = #{studentVitalInfoList.studentVitalInfo.id}",

			"studentVitalInfo.height = #{studentVitalInfoList.studentVitalInfo.height}",

			"studentVitalInfo.weight = #{studentVitalInfoList.studentVitalInfo.weight}",

			"studentVitalInfo.student.id = #{studentVitalInfoList.studentVitalInfo.student.id}",

			"studentVitalInfo.dateCreated <= #{studentVitalInfoList.dateCreatedRange.end}",
			"studentVitalInfo.dateCreated >= #{studentVitalInfoList.dateCreatedRange.begin}",};

	public List<StudentVitalInfo> getStudentVitalInfosByStudent(
			com.oreon.smartsis.domain.Student student) {
		//setMaxResults(10000);
		studentVitalInfo.setStudent(student);
		return getResultList();
	}

	@Observer("archivedStudentVitalInfo")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, StudentVitalInfo e) {

		builder.append("\"" + (e.getHeight() != null ? e.getHeight() : "")
				+ "\",");

		builder.append("\"" + (e.getWeight() != null ? e.getWeight() : "")
				+ "\",");

		builder.append("\""
				+ (e.getStudent() != null ? e.getStudent().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Height" + ",");

		builder.append("Weight" + ",");

		builder.append("Student" + ",");

		builder.append("\r\n");
	}
}
