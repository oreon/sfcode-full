package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.QuestionTranslation;

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

import com.hrb.tservices.domain.faq.QuestionTranslation;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class QuestionTranslationListQueryBase
		extends
			BaseQuery<QuestionTranslation, Long> {

	private static final String EJBQL = "select questionTranslation from QuestionTranslation questionTranslation";

	protected QuestionTranslation questionTranslation = new QuestionTranslation();

	public QuestionTranslation getQuestionTranslation() {
		return questionTranslation;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<QuestionTranslation> getEntityClass() {
		return QuestionTranslation.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"questionTranslation.id = #{questionTranslationList.questionTranslation.id}",

			"questionTranslation.language = #{questionTranslationList.questionTranslation.language}",

			"lower(questionTranslation.title) like concat(lower(#{questionTranslationList.questionTranslation.title}),'%')",

			"lower(questionTranslation.text) like concat(lower(#{questionTranslationList.questionTranslation.text}),'%')",

			"lower(questionTranslation.link) like concat(lower(#{questionTranslationList.questionTranslation.link}),'%')",

			"questionTranslation.faqQuestion.id = #{questionTranslationList.questionTranslation.faqQuestion.id}",

			"lower(questionTranslation.answer) like concat(lower(#{questionTranslationList.questionTranslation.answer}),'%')",

			"questionTranslation.dateCreated <= #{questionTranslationList.dateCreatedRange.end}",
			"questionTranslation.dateCreated >= #{questionTranslationList.dateCreatedRange.begin}",};

	public List<QuestionTranslation> getQuestionTranslationsByFaqQuestion(
			com.hrb.tservices.domain.faq.FaqQuestion faqQuestion) {
		//setMaxResults(10000);
		questionTranslation.setFaqQuestion(faqQuestion);
		return getResultList();
	}

	@Observer("archivedQuestionTranslation")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, QuestionTranslation e) {

		builder.append("\"" + (e.getLanguage() != null ? e.getLanguage() : "")
				+ "\",");

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getText() != null ? e.getText().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getLink() != null ? e.getLink().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getFaqQuestion() != null ? e.getFaqQuestion()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAnswer() != null ? e.getAnswer().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Language" + ",");

		builder.append("Title" + ",");

		builder.append("Text" + ",");

		builder.append("Link" + ",");

		builder.append("FaqQuestion" + ",");

		builder.append("Answer" + ",");

		builder.append("\r\n");
	}
}
