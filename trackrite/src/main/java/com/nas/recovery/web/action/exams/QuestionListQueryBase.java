package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Question;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.exams.Question;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class QuestionListQueryBase extends BaseQuery<Question, Long> {

	//private static final String EJBQL = "select question from Question question";

	protected Question question = new Question();

	private static final String[] RESTRICTIONS = {
			"question.id = #{questionList.question.id}",

			"lower(question.text) like concat(lower(#{questionList.question.text}),'%')",

			"question.exam.id = #{questionList.question.exam.id}",

			"question.dateCreated <= #{questionList.dateCreatedRange.end}",
			"question.dateCreated >= #{questionList.dateCreatedRange.begin}",};

	public Question getQuestion() {
		return question;
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedQuestion")
	public void onArchive() {
		refresh();
	}
}
