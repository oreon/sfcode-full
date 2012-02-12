package com.hrb.tservices.web.action.message;

import com.hrb.tservices.domain.message.MessageTranslation;

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

import com.hrb.tservices.domain.message.MessageTranslation;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MessageTranslationListQueryBase
		extends
			BaseQuery<MessageTranslation, Long> {

	private static final String EJBQL = "select messageTranslation from MessageTranslation messageTranslation";

	protected MessageTranslation messageTranslation = new MessageTranslation();

	public MessageTranslation getMessageTranslation() {
		return messageTranslation;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MessageTranslation> getEntityClass() {
		return MessageTranslation.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"messageTranslation.id = #{messageTranslationList.messageTranslation.id}",

			"lower(messageTranslation.buttonText) like concat(lower(#{messageTranslationList.messageTranslation.buttonText}),'%')",

			"lower(messageTranslation.message) like concat(lower(#{messageTranslationList.messageTranslation.message}),'%')",

			"lower(messageTranslation.hyperLink) like concat(lower(#{messageTranslationList.messageTranslation.hyperLink}),'%')",

			"messageTranslation.marketingMessage.id = #{messageTranslationList.messageTranslation.marketingMessage.id}",

			"messageTranslation.language = #{messageTranslationList.messageTranslation.language}",

			"messageTranslation.dateCreated <= #{messageTranslationList.dateCreatedRange.end}",
			"messageTranslation.dateCreated >= #{messageTranslationList.dateCreatedRange.begin}",};

	public List<MessageTranslation> getMessageTranslationsByMarketingMessage(
			com.hrb.tservices.domain.message.MarketingMessage marketingMessage) {
		//setMaxResults(10000);
		messageTranslation.setMarketingMessage(marketingMessage);
		return getResultList();
	}

	@Observer("archivedMessageTranslation")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MessageTranslation e) {

		builder.append("\""
				+ (e.getButtonText() != null ? e.getButtonText().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getMessage() != null
						? e.getMessage().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getHyperLink() != null
						? e.getHyperLink().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getMarketingMessage() != null ? e.getMarketingMessage()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getLanguage() != null ? e.getLanguage() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("ButtonText" + ",");

		builder.append("Message" + ",");

		builder.append("HyperLink" + ",");

		builder.append("MarketingMessage" + ",");

		builder.append("Language" + ",");

		builder.append("\r\n");
	}
}
