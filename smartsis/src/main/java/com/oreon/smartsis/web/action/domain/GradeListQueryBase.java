package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Grade;

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

import com.oreon.smartsis.domain.Grade;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GradeListQueryBase extends BaseQuery<Grade, Long> {

	private static final String EJBQL = "select grade from Grade grade";

	protected Grade grade = new Grade();

	public Grade getGrade() {
		return grade;
	}

	private com.oreon.smartsis.domain.Exam examsToSearch;

	public void setExamsToSearch(com.oreon.smartsis.domain.Exam examToSearch) {
		this.examsToSearch = examToSearch;
	}

	public com.oreon.smartsis.domain.Exam getExamsToSearch() {
		return examsToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Grade> getEntityClass() {
		return Grade.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> ordinalRange = new Range<Integer>();
	public Range<Integer> getOrdinalRange() {
		return ordinalRange;
	}
	public void setOrdinal(Range<Integer> ordinalRange) {
		this.ordinalRange = ordinalRange;
	}

	private static final String[] RESTRICTIONS = {
			"grade.id = #{gradeList.grade.id}",

			"lower(grade.name) like concat(lower(#{gradeList.grade.name}),'%')",

			"#{gradeList.examsToSearch} in elements(grade.exams)",

			"grade.ordinal >= #{gradeList.ordinalRange.begin}",
			"grade.ordinal <= #{gradeList.ordinalRange.end}",

			"lower(grade.section) like concat(lower(#{gradeList.grade.section}),'%')",

			"grade.dateCreated <= #{gradeList.dateCreatedRange.end}",
			"grade.dateCreated >= #{gradeList.dateCreatedRange.begin}",};

	@Observer("archivedGrade")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Grade e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getExams() != null ? e.getExams() : "")
				+ "\",");

		builder.append("\"" + (e.getOrdinal() != null ? e.getOrdinal() : "")
				+ "\",");

		builder.append("\""
				+ (e.getSection() != null
						? e.getSection().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Exams" + ",");

		builder.append("Ordinal" + ",");

		builder.append("Section" + ",");

		builder.append("\r\n");
	}
}
