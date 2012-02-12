package com.hrb.tservices.web.action.metrics;

import com.hrb.tservices.domain.metrics.RestService;

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

import com.hrb.tservices.domain.metrics.RestService;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RestServiceListQueryBase
		extends
			BaseQuery<RestService, Long> {

	private static final String EJBQL = "select restService from RestService restService";

	protected RestService restService = new RestService();

	public RestService getRestService() {
		return restService;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<RestService> getEntityClass() {
		return RestService.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"restService.id = #{restServiceList.restService.id}",

			"lower(restService.name) like concat(lower(#{restServiceList.restService.name}),'%')",

			"restService.dateCreated <= #{restServiceList.dateCreatedRange.end}",
			"restService.dateCreated >= #{restServiceList.dateCreatedRange.begin}",};

	@Observer("archivedRestService")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, RestService e) {

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

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
