package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Sponsor;

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

import com.oreon.smartsis.domain.Sponsor;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SponsorListQueryBase extends BaseQuery<Sponsor, Long> {

	private static final String EJBQL = "select sponsor from Sponsor sponsor";

	protected Sponsor sponsor = new Sponsor();

	public Sponsor getSponsor() {
		return sponsor;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Sponsor> getEntityClass() {
		return Sponsor.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"sponsor.id = #{sponsorList.sponsor.id}",

			"lower(sponsor.firstName) like concat(lower(#{sponsorList.sponsor.firstName}),'%')",

			"lower(sponsor.lastName) like concat(lower(#{sponsorList.sponsor.lastName}),'%')",

			"lower(sponsor.contactDetails.primaryPhone) like concat(lower(#{sponsorList.sponsor.contactDetails.primaryPhone}),'%')",

			"lower(sponsor.contactDetails.secondaryPhone) like concat(lower(#{sponsorList.sponsor.contactDetails.secondaryPhone}),'%')",

			"lower(sponsor.contactDetails.email) like concat(lower(#{sponsorList.sponsor.contactDetails.email}),'%')",

			"sponsor.dateCreated <= #{sponsorList.dateCreatedRange.end}",
			"sponsor.dateCreated >= #{sponsorList.dateCreatedRange.begin}",};

	@Observer("archivedSponsor")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Sponsor e) {

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("\r\n");
	}
}
