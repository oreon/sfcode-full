package com.hrb.tservices.web.action.offices;

import com.hrb.tservices.domain.offices.OfficeLocatorMetrics;

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

import com.hrb.tservices.domain.offices.OfficeLocatorMetrics;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class OfficeLocatorMetricsListQueryBase
		extends
			BaseQuery<OfficeLocatorMetrics, Long> {

	private static final String EJBQL = "select officeLocatorMetrics from OfficeLocatorMetrics officeLocatorMetrics";

	protected OfficeLocatorMetrics officeLocatorMetrics = new OfficeLocatorMetrics();

	public OfficeLocatorMetrics getOfficeLocatorMetrics() {
		return officeLocatorMetrics;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<OfficeLocatorMetrics> getEntityClass() {
		return OfficeLocatorMetrics.class;
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

	private static final String[] RESTRICTIONS = {
			"officeLocatorMetrics.id = #{officeLocatorMetricsList.officeLocatorMetrics.id}",

			"officeLocatorMetrics.restMethod.id = #{officeLocatorMetricsList.officeLocatorMetrics.restMethod.id}",

			"officeLocatorMetrics.partner.id = #{officeLocatorMetricsList.officeLocatorMetrics.partner.id}",

			"officeLocatorMetrics.clientType.id = #{officeLocatorMetricsList.officeLocatorMetrics.clientType.id}",

			"officeLocatorMetrics.date >= #{officeLocatorMetricsList.dateRange.begin}",
			"officeLocatorMetrics.date <= #{officeLocatorMetricsList.dateRange.end}",

			"officeLocatorMetrics.language = #{officeLocatorMetricsList.officeLocatorMetrics.language}",

			"lower(officeLocatorMetrics.sessionId) like concat(lower(#{officeLocatorMetricsList.officeLocatorMetrics.sessionId}),'%')",

			"lower(officeLocatorMetrics.lookupCity) like concat(lower(#{officeLocatorMetricsList.officeLocatorMetrics.lookupCity}),'%')",

			"lower(officeLocatorMetrics.lookupPostalCode) like concat(lower(#{officeLocatorMetricsList.officeLocatorMetrics.lookupPostalCode}),'%')",

			"officeLocatorMetrics.dateCreated <= #{officeLocatorMetricsList.dateCreatedRange.end}",
			"officeLocatorMetrics.dateCreated >= #{officeLocatorMetricsList.dateCreatedRange.begin}",};

	@Observer("archivedOfficeLocatorMetrics")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, OfficeLocatorMetrics e) {

		builder.append("\""
				+ (e.getLookupCity() != null ? e.getLookupCity().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getLookupPostalCode() != null ? e.getLookupPostalCode()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("LookupCity" + ",");

		builder.append("LookupPostalCode" + ",");

		builder.append("\r\n");
	}
}
