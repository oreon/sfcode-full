package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.TrackedVital;

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

import com.oreon.cerebrum.patient.TrackedVital;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TrackedVitalListQueryBase
		extends
			BaseQuery<TrackedVital, Long> {

	private static final String EJBQL = "select trackedVital from TrackedVital trackedVital";

	protected TrackedVital trackedVital = new TrackedVital();

	public TrackedVital getTrackedVital() {
		return trackedVital;
	}

	@Override
	public TrackedVital getInstance() {
		return getTrackedVital();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('trackedVital', 'view')}")
	public List<TrackedVital> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<TrackedVital> getEntityClass() {
		return TrackedVital.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> minValueRange = new Range<Double>();

	public Range<Double> getMinValueRange() {
		return minValueRange;
	}
	public void setMinValue(Range<Double> minValueRange) {
		this.minValueRange = minValueRange;
	}

	private Range<Double> maxValueRange = new Range<Double>();

	public Range<Double> getMaxValueRange() {
		return maxValueRange;
	}
	public void setMaxValue(Range<Double> maxValueRange) {
		this.maxValueRange = maxValueRange;
	}

	private static final String[] RESTRICTIONS = {
			"trackedVital.id = #{trackedVitalList.trackedVital.id}",

			"trackedVital.archived = #{trackedVitalList.trackedVital.archived}",

			"lower(trackedVital.name) like concat(lower(#{trackedVitalList.trackedVital.name}),'%')",

			"trackedVital.minValue >= #{trackedVitalList.minValueRange.begin}",
			"trackedVital.minValue <= #{trackedVitalList.minValueRange.end}",

			"trackedVital.maxValue >= #{trackedVitalList.maxValueRange.begin}",
			"trackedVital.maxValue <= #{trackedVitalList.maxValueRange.end}",

			"trackedVital.dateCreated <= #{trackedVitalList.dateCreatedRange.end}",
			"trackedVital.dateCreated >= #{trackedVitalList.dateCreatedRange.begin}",};

	@Observer("archivedTrackedVital")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, TrackedVital e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getMinValue() != null ? e.getMinValue() : "")
				+ "\",");

		builder.append("\"" + (e.getMaxValue() != null ? e.getMaxValue() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("MinValue" + ",");

		builder.append("MaxValue" + ",");

		builder.append("\r\n");
	}
}
