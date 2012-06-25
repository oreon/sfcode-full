package com.pcas.datapkg.web.action.dashboards;

import com.pcas.datapkg.dashboards.DashboardComponent;

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

import com.pcas.datapkg.dashboards.DashboardComponent;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DashboardComponentListQueryBase
		extends
			BaseQuery<DashboardComponent, Long> {

	private static final String EJBQL = "select dashboardComponent from DashboardComponent dashboardComponent";

	protected DashboardComponent dashboardComponent = new DashboardComponent();

	public DashboardComponent getDashboardComponent() {
		return dashboardComponent;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DashboardComponent> getEntityClass() {
		return DashboardComponent.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dashboardComponent.id = #{dashboardComponentList.dashboardComponent.id}",

			"dashboardComponent.dashboard.id = #{dashboardComponentList.dashboardComponent.dashboard.id}",

			"lower(dashboardComponent.contents) like concat(lower(#{dashboardComponentList.dashboardComponent.contents}),'%')",

			"dashboardComponent.dateCreated <= #{dashboardComponentList.dateCreatedRange.end}",
			"dashboardComponent.dateCreated >= #{dashboardComponentList.dateCreatedRange.begin}",};

	public List<DashboardComponent> getDashboardComponentsByDashboard(
			com.pcas.datapkg.dashboards.Dashboard dashboard) {
		//setMaxResults(10000);
		dashboardComponent.setDashboard(dashboard);
		return getResultList();
	}

	@Observer("archivedDashboardComponent")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DashboardComponent e) {

		builder.append("\""
				+ (e.getDashboard() != null ? e.getDashboard().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getContents() != null
						? e.getContents().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Dashboard" + ",");

		builder.append("Contents" + ",");

		builder.append("\r\n");
	}
}
