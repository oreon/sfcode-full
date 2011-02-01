package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.Sponsorship;

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

import com.oreon.tapovan.domain.Sponsorship;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SponsorshipListQueryBase
		extends
			BaseQuery<Sponsorship, Long> {

	private static final String EJBQL = "select sponsorship from Sponsorship sponsorship";

	protected Sponsorship sponsorship = new Sponsorship();

	public Sponsorship getSponsorship() {
		return sponsorship;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Sponsorship> getEntityClass() {
		return Sponsorship.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> amountRange = new Range<Double>();
	public Range<Double> getAmountRange() {
		return amountRange;
	}
	public void setAmount(Range<Double> amountRange) {
		this.amountRange = amountRange;
	}

	private static final String[] RESTRICTIONS = {
			"sponsorship.id = #{sponsorshipList.sponsorship.id}",

			"sponsorship.sponsor.id = #{sponsorshipList.sponsorship.sponsor.id}",

			"sponsorship.student.id = #{sponsorshipList.sponsorship.student.id}",

			"sponsorship.amount >= #{sponsorshipList.amountRange.begin}",
			"sponsorship.amount <= #{sponsorshipList.amountRange.end}",

			"sponsorship.dateCreated <= #{sponsorshipList.dateCreatedRange.end}",
			"sponsorship.dateCreated >= #{sponsorshipList.dateCreatedRange.begin}",};

	@Observer("archivedSponsorship")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Sponsorship e) {

		builder.append("\""
				+ (e.getSponsor() != null
						? e.getSponsor().getDisplayName()
						: "") + "\",");

		builder.append("\""
				+ (e.getStudent() != null
						? e.getStudent().getDisplayName()
						: "") + "\",");

		builder.append("\"" + (e.getAmount() != null ? e.getAmount() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Sponsor" + ",");

		builder.append("Student" + ",");

		builder.append("Amount" + ",");

		builder.append("\r\n");
	}
}
