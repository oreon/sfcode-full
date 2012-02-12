package com.hrb.tservices.web.action.message;

import com.hrb.tservices.domain.message.MarketingMessage;

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

import com.hrb.tservices.domain.message.MarketingMessage;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MarketingMessageListQueryBase
		extends
			BaseQuery<MarketingMessage, Long> {

	private static final String EJBQL = "select marketingMessage from MarketingMessage marketingMessage";

	protected MarketingMessage marketingMessage = new MarketingMessage();

	public MarketingMessage getMarketingMessage() {
		return marketingMessage;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MarketingMessage> getEntityClass() {
		return MarketingMessage.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"marketingMessage.id = #{marketingMessageList.marketingMessage.id}",

			"lower(marketingMessage.messageTitle) like concat(lower(#{marketingMessageList.marketingMessage.messageTitle}),'%')",

			"marketingMessage.dateCreated <= #{marketingMessageList.dateCreatedRange.end}",
			"marketingMessage.dateCreated >= #{marketingMessageList.dateCreatedRange.begin}",};

	@Observer("archivedMarketingMessage")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MarketingMessage e) {

		builder.append("\""
				+ (e.getMessageTitle() != null ? e.getMessageTitle().replace(
						",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("MessageTitle" + ",");

		builder.append("\r\n");
	}
}
