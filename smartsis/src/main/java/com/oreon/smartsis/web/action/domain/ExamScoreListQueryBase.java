package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.ExamScore;

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

import com.oreon.smartsis.domain.ExamScore;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ExamScoreListQueryBase extends BaseQuery<ExamScore, Long> {

	private static final String EJBQL = "select examScore from ExamScore examScore";

	protected ExamScore examScore = new ExamScore();

	public ExamScore getExamScore() {
		return examScore;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ExamScore> getEntityClass() {
		return ExamScore.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> marksRange = new Range<Integer>();
	public Range<Integer> getMarksRange() {
		return marksRange;
	}
	public void setMarks(Range<Integer> marksRange) {
		this.marksRange = marksRange;
	}

	private Range<Integer> rankRange = new Range<Integer>();
	public Range<Integer> getRankRange() {
		return rankRange;
	}
	public void setRank(Range<Integer> rankRange) {
		this.rankRange = rankRange;
	}

	private static final String[] RESTRICTIONS = {
			"examScore.id = #{examScoreList.examScore.id}",

			"examScore.examInstance.id = #{examScoreList.examScore.examInstance.id}",

			"examScore.marks >= #{examScoreList.marksRange.begin}",
			"examScore.marks <= #{examScoreList.marksRange.end}",

			"examScore.rank >= #{examScoreList.rankRange.begin}",
			"examScore.rank <= #{examScoreList.rankRange.end}",

			"examScore.student.id = #{examScoreList.examScore.student.id}",

			"examScore.dateCreated <= #{examScoreList.dateCreatedRange.end}",
			"examScore.dateCreated >= #{examScoreList.dateCreatedRange.begin}",};

	public List<ExamScore> getExamScoresByExamInstance(
			com.oreon.smartsis.domain.ExamInstance examInstance) {
		//setMaxResults(10000);
		examScore.setExamInstance(examInstance);
		return getResultList();
	}

	@Observer("archivedExamScore")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ExamScore e) {

		builder.append("\""
				+ (e.getExamInstance() != null ? e.getExamInstance()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getMarks() != null ? e.getMarks() : "")
				+ "\",");

		builder.append("\"" + (e.getRank() != null ? e.getRank() : "") + "\",");

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

		builder.append("ExamInstance" + ",");

		builder.append("Marks" + ",");

		builder.append("Rank" + ",");

		builder.append("Student" + ",");

		builder.append("\r\n");
	}
}
