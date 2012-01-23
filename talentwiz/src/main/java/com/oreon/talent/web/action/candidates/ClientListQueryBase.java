package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.Client;

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

import com.oreon.talent.candidates.Client;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ClientListQueryBase extends BaseQuery<Client, Long> {

	private static final String EJBQL = "select client from Client client";

	protected Client client = new Client();

	public Client getClient() {
		return client;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Client> getEntityClass() {
		return Client.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"client.id = #{clientList.client.id}",

			"lower(client.name) like concat(lower(#{clientList.client.name}),'%')",

			"client.dateCreated <= #{clientList.dateCreatedRange.end}",
			"client.dateCreated >= #{clientList.dateCreatedRange.begin}",};

	@Observer("archivedClient")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Client e) {

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
