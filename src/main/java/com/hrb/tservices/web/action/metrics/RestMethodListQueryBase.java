package com.hrb.tservices.web.action.metrics;

import com.hrb.tservices.domain.metrics.RestMethod;

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

import com.hrb.tservices.domain.metrics.RestMethod;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RestMethodListQueryBase
		extends
			BaseQuery<RestMethod, Long> {

	private static final String EJBQL = "select restMethod from RestMethod restMethod";

	protected RestMethod restMethod = new RestMethod();

	public RestMethod getRestMethod() {
		return restMethod;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<RestMethod> getEntityClass() {
		return RestMethod.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"restMethod.id = #{restMethodList.restMethod.id}",

			"restMethod.restService.id = #{restMethodList.restMethod.restService.id}",

			"lower(restMethod.name) like concat(lower(#{restMethodList.restMethod.name}),'%')",

			"restMethod.dateCreated <= #{restMethodList.dateCreatedRange.end}",
			"restMethod.dateCreated >= #{restMethodList.dateCreatedRange.begin}",};

	public List<RestMethod> getRestMethodsByRestService(
			com.hrb.tservices.domain.metrics.RestService restService) {
		//setMaxResults(10000);
		restMethod.setRestService(restService);
		return getResultList();
	}

	@Observer("archivedRestMethod")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, RestMethod e) {

		builder.append("\""
				+ (e.getRestService() != null ? e.getRestService()
						.getDisplayName().replace(",", "") : "") + "\",");

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

		builder.append("RestService" + ",");

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
