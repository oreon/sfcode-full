package com.nas.recovery.web.action.domain;

import org.wc.mytapovan.domain.Sponsoror;

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

import org.wc.mytapovan.domain.Sponsoror;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SponsororListQueryBase extends BaseQuery<Sponsoror, Long> {

	private static final String EJBQL = "select sponsoror from Sponsoror sponsoror";

	protected Sponsoror sponsoror = new Sponsoror();

	public Sponsoror getSponsoror() {
		return sponsoror;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Sponsoror> getEntityClass() {
		return Sponsoror.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"sponsoror.id = #{sponsororList.sponsoror.id}",

			"lower(sponsoror.firstName) like concat(lower(#{sponsororList.sponsoror.firstName}),'%')",

			"lower(sponsoror.lastName) like concat(lower(#{sponsororList.sponsoror.lastName}),'%')",

			"lower(sponsoror.contactDetails.primaryPhone) like concat(lower(#{sponsororList.sponsoror.contactDetails.primaryPhone}),'%')",

			"lower(sponsoror.contactDetails.secondaryPhone) like concat(lower(#{sponsororList.sponsoror.contactDetails.secondaryPhone}),'%')",

			"lower(sponsoror.contactDetails.email) like concat(lower(#{sponsororList.sponsoror.contactDetails.email}),'%')",

			"sponsoror.dateCreated <= #{sponsororList.dateCreatedRange.end}",
			"sponsoror.dateCreated >= #{sponsororList.dateCreatedRange.begin}",};

	@Observer("archivedSponsoror")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Sponsoror e) {

		builder.append("\""
				+ (e.getContactDetails() != null ? e.getContactDetails() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("ContactDetails" + ",");

		builder.append("\r\n");
	}
}
