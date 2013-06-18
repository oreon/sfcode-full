package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.ChartProcedure;

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

import com.oreon.cerebrum.charts.ChartProcedure;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChartProcedureListQueryBase
		extends
			BaseQuery<ChartProcedure, Long> {

	private static final String EJBQL = "select chartProcedure from ChartProcedure chartProcedure";

	protected ChartProcedure chartProcedure = new ChartProcedure();

	public ChartProcedure getChartProcedure() {
		return chartProcedure;
	}

	@Override
	public ChartProcedure getInstance() {
		return getChartProcedure();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('chartProcedure', 'view')}")
	public List<ChartProcedure> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ChartProcedure> getEntityClass() {
		return ChartProcedure.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"chartProcedure.id = #{chartProcedureList.chartProcedure.id}",

			"chartProcedure.archived = #{chartProcedureList.chartProcedure.archived}",

			"chartProcedure.patient.id = #{chartProcedureList.chartProcedure.patient.id}",

			"chartProcedure.chartItem.id = #{chartProcedureList.chartProcedure.chartItem.id}",

			"chartProcedure.dateCreated <= #{chartProcedureList.dateCreatedRange.end}",
			"chartProcedure.dateCreated >= #{chartProcedureList.dateCreatedRange.begin}",};

	public List<ChartProcedure> getChartProceduresByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		//setMaxResults(10000);
		chartProcedure.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedChartProcedure")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ChartProcedure e) {

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getChartItem() != null ? e.getChartItem().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Patient" + ",");

		builder.append("ChartItem" + ",");

		builder.append("\r\n");
	}
}
