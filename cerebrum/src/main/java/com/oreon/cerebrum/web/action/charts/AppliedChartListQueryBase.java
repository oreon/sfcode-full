package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.AppliedChart;

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

import com.oreon.cerebrum.charts.AppliedChart;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppliedChartListQueryBase
		extends
			BaseQuery<AppliedChart, Long> {

	private static final String EJBQL = "select appliedChart from AppliedChart appliedChart";

	protected AppliedChart appliedChart = new AppliedChart();

	public AppliedChart getAppliedChart() {
		return appliedChart;
	}

	@Override
	public AppliedChart getInstance() {
		return getAppliedChart();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('appliedChart', 'view')}")
	public List<AppliedChart> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AppliedChart> getEntityClass() {
		return AppliedChart.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"appliedChart.id = #{appliedChartList.appliedChart.id}",

			"appliedChart.archived = #{appliedChartList.appliedChart.archived}",

			"appliedChart.patient.id = #{appliedChartList.appliedChart.patient.id}",

			"appliedChart.chart.id = #{appliedChartList.appliedChart.chart.id}",

			"appliedChart.dateCreated <= #{appliedChartList.dateCreatedRange.end}",
			"appliedChart.dateCreated >= #{appliedChartList.dateCreatedRange.begin}",};

	public List<AppliedChart> getAppliedChartsByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		//setMaxResults(10000);
		appliedChart.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedAppliedChart")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, AppliedChart e) {

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getChart() != null ? e.getChart().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Patient" + ",");

		builder.append("Chart" + ",");

		builder.append("\r\n");
	}
}
