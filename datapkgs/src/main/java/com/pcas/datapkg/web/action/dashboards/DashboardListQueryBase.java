package com.pcas.datapkg.web.action.dashboards;

import com.pcas.datapkg.dashboards.Dashboard;

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

import com.pcas.datapkg.dashboards.Dashboard;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DashboardListQueryBase extends BaseQuery<Dashboard, Long> {

	private static final String EJBQL = "select dashboard from Dashboard dashboard";

	protected Dashboard dashboard = new Dashboard();

	public Dashboard getDashboard() {
		return dashboard;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Dashboard> getEntityClass() {
		return Dashboard.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dashboard.id = #{dashboardList.dashboard.id}",

			"dashboard.appUser.id = #{dashboardList.dashboard.appUser.id}",

			"lower(dashboard.name) like concat(lower(#{dashboardList.dashboard.name}),'%')",

			"dashboard.dateCreated <= #{dashboardList.dateCreatedRange.end}",
			"dashboard.dateCreated >= #{dashboardList.dateCreatedRange.begin}",};

	@Observer("archivedDashboard")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Dashboard e) {

		builder.append("\""
				+ (e.getAppUser() != null ? e.getAppUser().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("AppUser" + ",");

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
