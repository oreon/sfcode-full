package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.ChartItem;

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

import java.math.BigDecimal;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.charts.ChartItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChartItemListQueryBase extends BaseQuery<ChartItem, Long> {

	private static final String EJBQL = "select chartItem from ChartItem chartItem";

	protected ChartItem chartItem = new ChartItem();

	@In(create = true)
	ChartItemAction chartItemAction;

	public ChartItemListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public ChartItem getChartItem() {
		return chartItem;
	}

	@Override
	public ChartItem getInstance() {
		return getChartItem();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('chartItem', 'view')}")
	public List<ChartItem> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ChartItem> getEntityClass() {
		return ChartItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> durationRange = new Range<Integer>();

	public Range<Integer> getDurationRange() {
		return durationRange;
	}
	public void setDuration(Range<Integer> durationRange) {
		this.durationRange = durationRange;
	}

	private static final String[] RESTRICTIONS = {
			"chartItem.id = #{chartItemList.chartItem.id}",

			"chartItem.archived = #{chartItemList.chartItem.archived}",

			"lower(chartItem.name) like concat(lower(#{chartItemList.chartItem.name}),'%')",

			"chartItem.duration >= #{chartItemList.durationRange.begin}",
			"chartItem.duration <= #{chartItemList.durationRange.end}",

			"chartItem.chart.id = #{chartItemList.chartItem.chart.id}",

			"chartItem.frequencyPeriod = #{chartItemList.chartItem.frequencyPeriod}",

			"chartItem.dateCreated <= #{chartItemList.dateCreatedRange.end}",
			"chartItem.dateCreated >= #{chartItemList.dateCreatedRange.begin}",};

	public List<ChartItem> getChartItemsByChart(
			com.oreon.cerebrum.charts.Chart chart) {
		chartItem.setChart(chart);
		return getResultList();
	}

	@Observer("archivedChartItem")
	public void onArchive() {
		refresh();
	}

	public void setChartId(Long id) {
		if (chartItem.getChart() == null) {
			chartItem.setChart(new com.oreon.cerebrum.charts.Chart());
		}
		chartItem.getChart().setId(id);
	}

	public Long getChartId() {
		return chartItem.getChart() == null ? null : chartItem.getChart()
				.getId();
	}

	//@Restrict("#{s:hasPermission('chartItem', 'delete')}")
	public void archiveById(Long id) {
		chartItemAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ChartItem e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getDuration() != null ? e.getDuration() : "")
				+ "\",");

		builder.append("\""
				+ (e.getChart() != null ? e.getChart().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder
				.append("\""
						+ (e.getFrequencyPeriod() != null ? e
								.getFrequencyPeriod() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Duration" + ",");

		builder.append("Chart" + ",");

		builder.append("FrequencyPeriod" + ",");

		builder.append("\r\n");
	}
}
