package com.hrb.tservices.web.action.department;

import com.hrb.tservices.domain.department.Partner;

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

import com.hrb.tservices.domain.department.Partner;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PartnerListQueryBase extends BaseQuery<Partner, Long> {

	private static final String EJBQL = "select partner from Partner partner";

	protected Partner partner = new Partner();

	public Partner getPartner() {
		return partner;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Partner> getEntityClass() {
		return Partner.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> appUser_lastLoginRange = new Range<Date>();
	public Range<Date> getAppUser_lastLoginRange() {
		return appUser_lastLoginRange;
	}
	public void setAppUser_lastLogin(Range<Date> appUser_lastLoginRange) {
		this.appUser_lastLoginRange = appUser_lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"partner.id = #{partnerList.partner.id}",

			"lower(partner.name) like concat(lower(#{partnerList.partner.name}),'%')",

			"lower(partner.partnerId) like concat(lower(#{partnerList.partner.partnerId}),'%')",

			"partner.marketingMessage.id = #{partnerList.partner.marketingMessage.id}",

			"lower(partner.appUser.userName) like concat(lower(#{partnerList.partner.appUser.userName}),'%')",

			"partner.appUser.enabled = #{partnerList.partner.appUser.enabled}",

			"lower(partner.appUser.email) like concat(lower(#{partnerList.partner.appUser.email}),'%')",

			"partner.appUser.lastLogin >= #{partnerList.appUser_lastLoginRange.begin}",
			"partner.appUser.lastLogin <= #{partnerList.appUser_lastLoginRange.end}",

			"partner.defaultLanguage = #{partnerList.partner.defaultLanguage}",

			"partner.dateCreated <= #{partnerList.dateCreatedRange.end}",
			"partner.dateCreated >= #{partnerList.dateCreatedRange.begin}",};

	@Observer("archivedPartner")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Partner e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getPartnerId() != null
						? e.getPartnerId().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getMarketingMessage() != null ? e.getMarketingMessage()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAppUser() != null ? e.getAppUser().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder
				.append("\""
						+ (e.getDefaultLanguage() != null ? e
								.getDefaultLanguage() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("PartnerId" + ",");

		builder.append("MarketingMessage" + ",");

		builder.append("AppUser" + ",");

		builder.append("DefaultLanguage" + ",");

		builder.append("\r\n");
	}
}
