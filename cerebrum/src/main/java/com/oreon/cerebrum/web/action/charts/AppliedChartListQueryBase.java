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

import org.jboss.seam.annotations.In;

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

	@In(create = true)
	AppliedChartAction appliedChartAction;

	public AppliedChartListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

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
		appliedChart.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedAppliedChart")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (appliedChart.getPatient() == null) {
			appliedChart.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		appliedChart.getPatient().setId(id);
	}

	public Long getPatientId() {
		return appliedChart.getPatient() == null ? null : appliedChart
				.getPatient().getId();
	}

	public void setChartId(Long id) {
		if (appliedChart.getChart() == null) {
			appliedChart.setChart(new com.oreon.cerebrum.charts.Chart());
		}
		appliedChart.getChart().setId(id);
	}

	public Long getChartId() {
		return appliedChart.getChart() == null ? null : appliedChart.getChart()
				.getId();
	}

	//@Restrict("#{s:hasPermission('appliedChart', 'delete')}")
	public void archiveById(Long id) {
		appliedChartAction.archiveById(id);
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
