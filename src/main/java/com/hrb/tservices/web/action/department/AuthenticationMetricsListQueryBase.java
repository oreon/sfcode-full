package com.hrb.tservices.web.action.department;

import com.hrb.tservices.domain.department.AuthenticationMetrics;

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

import com.hrb.tservices.domain.department.AuthenticationMetrics;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AuthenticationMetricsListQueryBase
		extends
			BaseQuery<AuthenticationMetrics, Long> {

	private static final String EJBQL = "select authenticationMetrics from AuthenticationMetrics authenticationMetrics";

	protected AuthenticationMetrics authenticationMetrics = new AuthenticationMetrics();

	public AuthenticationMetrics getAuthenticationMetrics() {
		return authenticationMetrics;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<AuthenticationMetrics> getEntityClass() {
		return AuthenticationMetrics.class;
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

	private Range<Date> logoutDateRange = new Range<Date>();
	public Range<Date> getLogoutDateRange() {
		return logoutDateRange;
	}
	public void setLogoutDate(Range<Date> logoutDateRange) {
		this.logoutDateRange = logoutDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"authenticationMetrics.id = #{authenticationMetricsList.authenticationMetrics.id}",

			"authenticationMetrics.restMethod.id = #{authenticationMetricsList.authenticationMetrics.restMethod.id}",

			"authenticationMetrics.partner.id = #{authenticationMetricsList.authenticationMetrics.partner.id}",

			"authenticationMetrics.clientType.id = #{authenticationMetricsList.authenticationMetrics.clientType.id}",

			"authenticationMetrics.date >= #{authenticationMetricsList.dateRange.begin}",
			"authenticationMetrics.date <= #{authenticationMetricsList.dateRange.end}",

			"authenticationMetrics.language = #{authenticationMetricsList.authenticationMetrics.language}",

			"lower(authenticationMetrics.sessionId) like concat(lower(#{authenticationMetricsList.authenticationMetrics.sessionId}),'%')",

			"authenticationMetrics.succeeded = #{authenticationMetricsList.authenticationMetrics.succeeded}",

			"authenticationMetrics.logoutDate >= #{authenticationMetricsList.logoutDateRange.begin}",
			"authenticationMetrics.logoutDate <= #{authenticationMetricsList.logoutDateRange.end}",

			"authenticationMetrics.dateCreated <= #{authenticationMetricsList.dateCreatedRange.end}",
			"authenticationMetrics.dateCreated >= #{authenticationMetricsList.dateCreatedRange.begin}",};

	@Observer("archivedAuthenticationMetrics")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, AuthenticationMetrics e) {

		builder.append("\""
				+ (e.getSucceeded() != null ? e.getSucceeded() : "") + "\",");

		builder.append("\""
				+ (e.getLogoutDate() != null ? e.getLogoutDate() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Succeeded" + ",");

		builder.append("LogoutDate" + ",");

		builder.append("\r\n");
	}
}
