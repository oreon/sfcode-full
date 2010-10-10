package com.nas.recovery.web.action.onepack;

import org.wc.trackrite.onepack.Answer;

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

import org.wc.trackrite.onepack.Answer;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AnswerListQueryBase extends BaseQuery<Answer, Long> {

	//private static final String EJBQL = "select answer from Answer answer";

	protected Answer answer = new Answer();

	private static final String[] RESTRICTIONS = {
			"answer.id = #{answerList.answer.id}",

			"answer.question.id = #{answerList.answer.question.id}",

			"answer.choice.id = #{answerList.answer.choice.id}",

			"answer.examInstance.id = #{answerList.answer.examInstance.id}",

			"answer.dateCreated <= #{answerList.dateCreatedRange.end}",
			"answer.dateCreated >= #{answerList.dateCreatedRange.begin}",};

	public Answer getAnswer() {
		return answer;
	}

	@Override
	public Class<Answer> getEntityClass() {
		return Answer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedAnswer")
	public void onArchive() {
		refresh();
	}
}
