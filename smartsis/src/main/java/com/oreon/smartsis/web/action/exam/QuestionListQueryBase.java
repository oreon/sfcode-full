package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.Question;

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

import com.oreon.smartsis.exam.Question;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class QuestionListQueryBase extends BaseQuery<Question, Long> {

	private static final String EJBQL = "select question from Question question";

	protected Question question = new Question();

	public Question getQuestion() {
		return question;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"question.id = #{questionList.question.id}",

			"lower(question.question) like concat(lower(#{questionList.question.question}),'%')",

			"question.electronicExam.id = #{questionList.question.electronicExam.id}",

			"question.correctChoice = #{questionList.question.correctChoice}",

			"question.dateCreated <= #{questionList.dateCreatedRange.end}",
			"question.dateCreated >= #{questionList.dateCreatedRange.begin}",};

	public List<Question> getQuestionsByElectronicExam(
			com.oreon.smartsis.exam.ElectronicExam electronicExam) {
		//setMaxResults(10000);
		question.setElectronicExam(electronicExam);
		return getResultList();
	}

	@Observer("archivedQuestion")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Question e) {

		builder.append("\""
				+ (e.getQuestion() != null
						? e.getQuestion().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getElectronicExam() != null ? e.getElectronicExam()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getCorrectChoice() != null ? e.getCorrectChoice() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Question" + ",");

		builder.append("ElectronicExam" + ",");

		builder.append("CorrectChoice" + ",");

		builder.append("\r\n");
	}
}
