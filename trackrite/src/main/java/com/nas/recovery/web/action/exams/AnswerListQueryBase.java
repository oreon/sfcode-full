package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Answer;

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

import org.wc.trackrite.exams.Answer;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AnswerListQueryBase extends BaseQuery<Answer, Long> {

	private static final String EJBQL = "select answer from Answer answer";

	protected Answer answer = new Answer();

	public Answer getAnswer() {
		return answer;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Answer> getEntityClass() {
		return Answer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"answer.id = #{answerList.answer.id}",

			"answer.choice.id = #{answerList.answer.choice.id}",

			"answer.examInstance.id = #{answerList.answer.examInstance.id}",

			"answer.dateCreated <= #{answerList.dateCreatedRange.end}",
			"answer.dateCreated >= #{answerList.dateCreatedRange.begin}",};

	public List<Answer> getAnswersByExamInstance(
			org.wc.trackrite.exams.ExamInstance examInstance) {
		//setMaxResults(10000);
		answer.setExamInstance(examInstance);
		return getResultList();
	}

	@Observer("archivedAnswer")
	public void onArchive() {
		refresh();
	}

}
