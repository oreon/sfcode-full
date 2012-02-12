package com.hrb.tservices.web.action.metrics;

import com.hrb.tservices.domain.metrics.ClientType;

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

import com.hrb.tservices.domain.metrics.ClientType;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ClientTypeListQueryBase
		extends
			BaseQuery<ClientType, Long> {

	private static final String EJBQL = "select clientType from ClientType clientType";

	protected ClientType clientType = new ClientType();

	public ClientType getClientType() {
		return clientType;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ClientType> getEntityClass() {
		return ClientType.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"clientType.id = #{clientTypeList.clientType.id}",

			"lower(clientType.name) like concat(lower(#{clientTypeList.clientType.name}),'%')",

			"clientType.dateCreated <= #{clientTypeList.dateCreatedRange.end}",
			"clientType.dateCreated >= #{clientTypeList.dateCreatedRange.begin}",};

	@Observer("archivedClientType")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ClientType e) {

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
