package com.hrb.tservices.web.action.message;

import com.hrb.tservices.domain.message.MarketingMessageMetrics;

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

import com.hrb.tservices.domain.message.MarketingMessageMetrics;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MarketingMessageMetricsListQueryBase
		extends
			BaseQuery<MarketingMessageMetrics, Long> {

	private static final String EJBQL = "select marketingMessageMetrics from MarketingMessageMetrics marketingMessageMetrics";

	protected MarketingMessageMetrics marketingMessageMetrics = new MarketingMessageMetrics();

	public MarketingMessageMetrics getMarketingMessageMetrics() {
		return marketingMessageMetrics;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MarketingMessageMetrics> getEntityClass() {
		return MarketingMessageMetrics.class;
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

	private Range<Date> dateClickedRange = new Range<Date>();
	public Range<Date> getDateClickedRange() {
		return dateClickedRange;
	}
	public void setDateClicked(Range<Date> dateClickedRange) {
		this.dateClickedRange = dateClickedRange;
	}

	private static final String[] RESTRICTIONS = {
			"marketingMessageMetrics.id = #{marketingMessageMetricsList.marketingMessageMetrics.id}",

			"marketingMessageMetrics.restMethod.id = #{marketingMessageMetricsList.marketingMessageMetrics.restMethod.id}",

			"marketingMessageMetrics.partner.id = #{marketingMessageMetricsList.marketingMessageMetrics.partner.id}",

			"marketingMessageMetrics.clientType.id = #{marketingMessageMetricsList.marketingMessageMetrics.clientType.id}",

			"marketingMessageMetrics.date >= #{marketingMessageMetricsList.dateRange.begin}",
			"marketingMessageMetrics.date <= #{marketingMessageMetricsList.dateRange.end}",

			"marketingMessageMetrics.language = #{marketingMessageMetricsList.marketingMessageMetrics.language}",

			"lower(marketingMessageMetrics.sessionId) like concat(lower(#{marketingMessageMetricsList.marketingMessageMetrics.sessionId}),'%')",

			"marketingMessageMetrics.dateClicked >= #{marketingMessageMetricsList.dateClickedRange.begin}",
			"marketingMessageMetrics.dateClicked <= #{marketingMessageMetricsList.dateClickedRange.end}",

			"marketingMessageMetrics.messageTranslation.id = #{marketingMessageMetricsList.marketingMessageMetrics.messageTranslation.id}",

			"marketingMessageMetrics.dateCreated <= #{marketingMessageMetricsList.dateCreatedRange.end}",
			"marketingMessageMetrics.dateCreated >= #{marketingMessageMetricsList.dateCreatedRange.begin}",};

	@Observer("archivedMarketingMessageMetrics")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MarketingMessageMetrics e) {

		builder.append("\""
				+ (e.getDateClicked() != null ? e.getDateClicked() : "")
				+ "\",");

		builder.append("\""
				+ (e.getMessageTranslation() != null ? e
						.getMessageTranslation().getDisplayName().replace(",",
								"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("DateClicked" + ",");

		builder.append("MessageTranslation" + ",");

		builder.append("\r\n");
	}
}
