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

import org.jboss.seam.annotations.In;

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

	@In(create = true)
	ChartProcedureAction chartProcedureAction;

	public ChartProcedureListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

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

	private Range<Date> dueDateRange = new Range<Date>();

	public Range<Date> getDueDateRange() {
		return dueDateRange;
	}
	public void setDueDate(Range<Date> dueDateRange) {
		this.dueDateRange = dueDateRange;
	}

	private Range<Date> datePerformedRange = new Range<Date>();

	public Range<Date> getDatePerformedRange() {
		return datePerformedRange;
	}
	public void setDatePerformed(Range<Date> datePerformedRange) {
		this.datePerformedRange = datePerformedRange;
	}

	private static final String[] RESTRICTIONS = {
			"chartProcedure.id = #{chartProcedureList.chartProcedure.id}",

			"chartProcedure.archived = #{chartProcedureList.chartProcedure.archived}",

			"chartProcedure.patient.id = #{chartProcedureList.chartProcedure.patient.id}",

			"chartProcedure.chartItem.id = #{chartProcedureList.chartProcedure.chartItem.id}",

			"chartProcedure.dueDate >= #{chartProcedureList.dueDateRange.begin}",
			"chartProcedure.dueDate <= #{chartProcedureList.dueDateRange.end}",

			"chartProcedure.datePerformed >= #{chartProcedureList.datePerformedRange.begin}",
			"chartProcedure.datePerformed <= #{chartProcedureList.datePerformedRange.end}",

			"lower(chartProcedure.remarks) like concat(lower(#{chartProcedureList.chartProcedure.remarks}),'%')",

			"chartProcedure.dateCreated <= #{chartProcedureList.dateCreatedRange.end}",
			"chartProcedure.dateCreated >= #{chartProcedureList.dateCreatedRange.begin}",};

	public List<ChartProcedure> getChartProceduresByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		chartProcedure.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedChartProcedure")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (chartProcedure.getPatient() == null) {
			chartProcedure.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		chartProcedure.getPatient().setId(id);
	}

	public Long getPatientId() {
		return chartProcedure.getPatient() == null ? null : chartProcedure
				.getPatient().getId();
	}

	public void setChartItemId(Long id) {
		if (chartProcedure.getChartItem() == null) {
			chartProcedure
					.setChartItem(new com.oreon.cerebrum.charts.ChartItem());
		}
		chartProcedure.getChartItem().setId(id);
	}

	public Long getChartItemId() {
		return chartProcedure.getChartItem() == null ? null : chartProcedure
				.getChartItem().getId();
	}

	//@Restrict("#{s:hasPermission('chartProcedure', 'delete')}")
	public void archiveById(Long id) {
		chartProcedureAction.archiveById(id);
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

		builder.append("\"" + (e.getDueDate() != null ? e.getDueDate() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDatePerformed() != null ? e.getDatePerformed() : "")
				+ "\",");

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Patient" + ",");

		builder.append("ChartItem" + ",");

		builder.append("DueDate" + ",");

		builder.append("DatePerformed" + ",");

		builder.append("Remarks" + ",");

		builder.append("\r\n");
	}
}
