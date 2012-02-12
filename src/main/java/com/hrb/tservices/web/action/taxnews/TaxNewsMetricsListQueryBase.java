package com.hrb.tservices.web.action.taxnews;

import com.hrb.tservices.domain.taxnews.TaxNewsMetrics;

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

import com.hrb.tservices.domain.taxnews.TaxNewsMetrics;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TaxNewsMetricsListQueryBase
		extends
			BaseQuery<TaxNewsMetrics, Long> {

	private static final String EJBQL = "select taxNewsMetrics from TaxNewsMetrics taxNewsMetrics";

	protected TaxNewsMetrics taxNewsMetrics = new TaxNewsMetrics();

	public TaxNewsMetrics getTaxNewsMetrics() {
		return taxNewsMetrics;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<TaxNewsMetrics> getEntityClass() {
		return TaxNewsMetrics.class;
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
			"taxNewsMetrics.id = #{taxNewsMetricsList.taxNewsMetrics.id}",

			"taxNewsMetrics.restMethod.id = #{taxNewsMetricsList.taxNewsMetrics.restMethod.id}",

			"taxNewsMetrics.partner.id = #{taxNewsMetricsList.taxNewsMetrics.partner.id}",

			"taxNewsMetrics.clientType.id = #{taxNewsMetricsList.taxNewsMetrics.clientType.id}",

			"taxNewsMetrics.date >= #{taxNewsMetricsList.dateRange.begin}",
			"taxNewsMetrics.date <= #{taxNewsMetricsList.dateRange.end}",

			"taxNewsMetrics.language = #{taxNewsMetricsList.taxNewsMetrics.language}",

			"lower(taxNewsMetrics.sessionId) like concat(lower(#{taxNewsMetricsList.taxNewsMetrics.sessionId}),'%')",

			"taxNewsMetrics.taxNews.id = #{taxNewsMetricsList.taxNewsMetrics.taxNews.id}",

			"taxNewsMetrics.dateViewed >= #{taxNewsMetricsList.dateViewedRange.begin}",
			"taxNewsMetrics.dateViewed <= #{taxNewsMetricsList.dateViewedRange.end}",

			"taxNewsMetrics.dateCreated <= #{taxNewsMetricsList.dateCreatedRange.end}",
			"taxNewsMetrics.dateCreated >= #{taxNewsMetricsList.dateCreatedRange.begin}",};

	@Observer("archivedTaxNewsMetrics")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, TaxNewsMetrics e) {

		builder.append("\""
				+ (e.getTaxNews() != null ? e.getTaxNews().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDateViewed() != null ? e.getDateViewed() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("TaxNews" + ",");

		builder.append("DateViewed" + ",");

		builder.append("\r\n");
	}
}
