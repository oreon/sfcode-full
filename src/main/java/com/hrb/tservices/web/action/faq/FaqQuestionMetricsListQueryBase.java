package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.FaqQuestionMetrics;

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

import com.hrb.tservices.domain.faq.FaqQuestionMetrics;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FaqQuestionMetricsListQueryBase
		extends
			BaseQuery<FaqQuestionMetrics, Long> {

	private static final String EJBQL = "select faqQuestionMetrics from FaqQuestionMetrics faqQuestionMetrics";

	protected FaqQuestionMetrics faqQuestionMetrics = new FaqQuestionMetrics();

	public FaqQuestionMetrics getFaqQuestionMetrics() {
		return faqQuestionMetrics;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FaqQuestionMetrics> getEntityClass() {
		return FaqQuestionMetrics.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateRange = new Range<Date>();
	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private Range<Date> dateViewedRange = new Range<Date>();
	public Range<Date> getDateViewedRange() {
		return dateViewedRange;
	}
	public void setDateViewed(Range<Date> dateViewedRange) {
		this.dateViewedRange = dateViewedRange;
	}

	private static final String[] RESTRICTIONS = {
			"faqQuestionMetrics.id = #{faqQuestionMetricsList.faqQuestionMetrics.id}",

			"faqQuestionMetrics.restMethod.id = #{faqQuestionMetricsList.faqQuestionMetrics.restMethod.id}",

			"faqQuestionMetrics.partner.id = #{faqQuestionMetricsList.faqQuestionMetrics.partner.id}",

			"faqQuestionMetrics.clientType.id = #{faqQuestionMetricsList.faqQuestionMetrics.clientType.id}",

			"faqQuestionMetrics.date >= #{faqQuestionMetricsList.dateRange.begin}",
			"faqQuestionMetrics.date <= #{faqQuestionMetricsList.dateRange.end}",

			"faqQuestionMetrics.language = #{faqQuestionMetricsList.faqQuestionMetrics.language}",

			"lower(faqQuestionMetrics.sessionId) like concat(lower(#{faqQuestionMetricsList.faqQuestionMetrics.sessionId}),'%')",

			"faqQuestionMetrics.faqQuestion.id = #{faqQuestionMetricsList.faqQuestionMetrics.faqQuestion.id}",

			"faqQuestionMetrics.dateViewed >= #{faqQuestionMetricsList.dateViewedRange.begin}",
			"faqQuestionMetrics.dateViewed <= #{faqQuestionMetricsList.dateViewedRange.end}",

			"faqQuestionMetrics.dateCreated <= #{faqQuestionMetricsList.dateCreatedRange.end}",
			"faqQuestionMetrics.dateCreated >= #{faqQuestionMetricsList.dateCreatedRange.begin}",};

	@Observer("archivedFaqQuestionMetrics")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FaqQuestionMetrics e) {

		builder.append("\""
				+ (e.getFaqQuestion() != null ? e.getFaqQuestion()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDateViewed() != null ? e.getDateViewed() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("FaqQuestion" + ",");

		builder.append("DateViewed" + ",");

		builder.append("\r\n");
	}
}
