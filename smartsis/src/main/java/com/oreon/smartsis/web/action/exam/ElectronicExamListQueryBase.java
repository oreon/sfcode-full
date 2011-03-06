package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.ElectronicExam;

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

import com.oreon.smartsis.exam.ElectronicExam;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ElectronicExamListQueryBase
		extends
			BaseQuery<ElectronicExam, Long> {

	private static final String EJBQL = "select electronicExam from ElectronicExam electronicExam";

	protected ElectronicExam electronicExam = new ElectronicExam();

	public ElectronicExam getElectronicExam() {
		return electronicExam;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ElectronicExam> getEntityClass() {
		return ElectronicExam.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> numberOfQuestionsRange = new Range<Integer>();
	public Range<Integer> getNumberOfQuestionsRange() {
		return numberOfQuestionsRange;
	}
	public void setNumberOfQuestions(Range<Integer> numberOfQuestionsRange) {
		this.numberOfQuestionsRange = numberOfQuestionsRange;
	}

	private Range<Integer> maxDurationRange = new Range<Integer>();
	public Range<Integer> getMaxDurationRange() {
		return maxDurationRange;
	}
	public void setMaxDuration(Range<Integer> maxDurationRange) {
		this.maxDurationRange = maxDurationRange;
	}

	private static final String[] RESTRICTIONS = {
			"electronicExam.id = #{electronicExamList.electronicExam.id}",

			"lower(electronicExam.name) like concat(lower(#{electronicExamList.electronicExam.name}),'%')",

			"electronicExam.numberOfQuestions >= #{electronicExamList.numberOfQuestionsRange.begin}",
			"electronicExam.numberOfQuestions <= #{electronicExamList.numberOfQuestionsRange.end}",

			"electronicExam.gradeSubject.id = #{electronicExamList.electronicExam.gradeSubject.id}",

			"electronicExam.maxDuration >= #{electronicExamList.maxDurationRange.begin}",
			"electronicExam.maxDuration <= #{electronicExamList.maxDurationRange.end}",

			"electronicExam.dateCreated <= #{electronicExamList.dateCreatedRange.end}",
			"electronicExam.dateCreated >= #{electronicExamList.dateCreatedRange.begin}",};

	@Observer("archivedElectronicExam")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ElectronicExam e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getNumberOfQuestions() != null
						? e.getNumberOfQuestions()
						: "") + "\",");

		builder.append("\""
				+ (e.getGradeSubject() != null ? e.getGradeSubject()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getMaxDuration() != null ? e.getMaxDuration() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("NumberOfQuestions" + ",");

		builder.append("GradeSubject" + ",");

		builder.append("MaxDuration" + ",");

		builder.append("\r\n");
	}
}
