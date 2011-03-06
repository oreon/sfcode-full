package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.ExamInstance;

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

import com.oreon.smartsis.domain.ExamInstance;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ExamInstanceListQueryBase
		extends
			BaseQuery<ExamInstance, Long> {

	private static final String EJBQL = "select examInstance from ExamInstance examInstance";

	protected ExamInstance examInstance = new ExamInstance();

	public ExamInstance getExamInstance() {
		return examInstance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ExamInstance> getEntityClass() {
		return ExamInstance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateHeldRange = new Range<Date>();
	public Range<Date> getDateHeldRange() {
		return dateHeldRange;
	}
	public void setDateHeld(Range<Date> dateHeldRange) {
		this.dateHeldRange = dateHeldRange;
	}

	private static final String[] RESTRICTIONS = {
			"examInstance.id = #{examInstanceList.examInstance.id}",

			"examInstance.exam.id = #{examInstanceList.examInstance.exam.id}",

			"examInstance.gradeSubject.id = #{examInstanceList.examInstance.gradeSubject.id}",

			"examInstance.dateHeld >= #{examInstanceList.dateHeldRange.begin}",
			"examInstance.dateHeld <= #{examInstanceList.dateHeldRange.end}",

			"examInstance.dateCreated <= #{examInstanceList.dateCreatedRange.end}",
			"examInstance.dateCreated >= #{examInstanceList.dateCreatedRange.begin}",};

	@Observer("archivedExamInstance")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ExamInstance e) {

		builder.append("\""
				+ (e.getExam() != null ? e.getExam().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getGradeSubject() != null ? e.getGradeSubject()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getDateHeld() != null ? e.getDateHeld() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Exam" + ",");

		builder.append("GradeSubject" + ",");

		builder.append("DateHeld" + ",");

		builder.append("\r\n");
	}
}
