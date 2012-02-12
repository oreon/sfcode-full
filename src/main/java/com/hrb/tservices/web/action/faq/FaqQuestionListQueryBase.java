package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.FaqQuestion;

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

import com.hrb.tservices.domain.faq.FaqQuestion;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FaqQuestionListQueryBase
		extends
			BaseQuery<FaqQuestion, Long> {

	private static final String EJBQL = "select faqQuestion from FaqQuestion faqQuestion";

	protected FaqQuestion faqQuestion = new FaqQuestion();

	public FaqQuestion getFaqQuestion() {
		return faqQuestion;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FaqQuestion> getEntityClass() {
		return FaqQuestion.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> avgRatingRange = new Range<Integer>();
	public Range<Integer> getAvgRatingRange() {
		return avgRatingRange;
	}
	public void setAvgRating(Range<Integer> avgRatingRange) {
		this.avgRatingRange = avgRatingRange;
	}

	private Range<Integer> viewsRange = new Range<Integer>();
	public Range<Integer> getViewsRange() {
		return viewsRange;
	}
	public void setViews(Range<Integer> viewsRange) {
		this.viewsRange = viewsRange;
	}

	private static final String[] RESTRICTIONS = {
			"faqQuestion.id = #{faqQuestionList.faqQuestion.id}",

			"faqQuestion.faqCategory.id = #{faqQuestionList.faqQuestion.faqCategory.id}",

			"lower(faqQuestion.title) like concat(lower(#{faqQuestionList.faqQuestion.title}),'%')",

			"faqQuestion.avgRating >= #{faqQuestionList.avgRatingRange.begin}",
			"faqQuestion.avgRating <= #{faqQuestionList.avgRatingRange.end}",

			"faqQuestion.inactive = #{faqQuestionList.faqQuestion.inactive}",

			"faqQuestion.views >= #{faqQuestionList.viewsRange.begin}",
			"faqQuestion.views <= #{faqQuestionList.viewsRange.end}",

			"faqQuestion.dateCreated <= #{faqQuestionList.dateCreatedRange.end}",
			"faqQuestion.dateCreated >= #{faqQuestionList.dateCreatedRange.begin}",};

	public List<FaqQuestion> getFaqQuestionsByFaqCategory(
			com.hrb.tservices.domain.faq.FaqCategory faqCategory) {
		//setMaxResults(10000);
		faqQuestion.setFaqCategory(faqCategory);
		return getResultList();
	}

	@Observer("archivedFaqQuestion")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FaqQuestion e) {

		builder.append("\""
				+ (e.getFaqCategory() != null ? e.getFaqCategory()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getAvgRating() != null ? e.getAvgRating() : "") + "\",");

		builder.append("\"" + (e.getInactive() != null ? e.getInactive() : "")
				+ "\",");

		builder.append("\"" + (e.getViews() != null ? e.getViews() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("FaqCategory" + ",");

		builder.append("Title" + ",");

		builder.append("AvgRating" + ",");

		builder.append("Inactive" + ",");

		builder.append("Views" + ",");

		builder.append("\r\n");
	}
}
