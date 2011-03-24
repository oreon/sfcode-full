package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.Claim;

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

import com.pwc.insuranceclaims.quickclaim.Claim;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ClaimListQueryBase extends BaseQuery<Claim, Long> {

	private static final String EJBQL = "select claim from Claim claim";

	protected Claim claim = new Claim();

	public Claim getClaim() {
		return claim;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Claim> getEntityClass() {
		return Claim.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> claimDateRange = new Range<Date>();
	public Range<Date> getClaimDateRange() {
		return claimDateRange;
	}
	public void setClaimDate(Range<Date> claimDateRange) {
		this.claimDateRange = claimDateRange;
	}

	private Range<Double> claimAmountRange = new Range<Double>();
	public Range<Double> getClaimAmountRange() {
		return claimAmountRange;
	}
	public void setClaimAmount(Range<Double> claimAmountRange) {
		this.claimAmountRange = claimAmountRange;
	}

	private static final String[] RESTRICTIONS = {
			"claim.id = #{claimList.claim.id}",

			"claim.policy.id = #{claimList.claim.policy.id}",

			"lower(claim.claimNumber) like concat(lower(#{claimList.claim.claimNumber}),'%')",

			"lower(claim.summary) like concat(lower(#{claimList.claim.summary}),'%')",

			"claim.claimDate >= #{claimList.claimDateRange.begin}",
			"claim.claimDate <= #{claimList.claimDateRange.end}",

			"claim.claimAmount >= #{claimList.claimAmountRange.begin}",
			"claim.claimAmount <= #{claimList.claimAmountRange.end}",

			"lower(claim.claimDescription) like concat(lower(#{claimList.claim.claimDescription}),'%')",

			"claim.status = #{claimList.claim.status}",

			"claim.claimPatient.id = #{claimList.claim.claimPatient.id}",

			"claim.dateCreated <= #{claimList.dateCreatedRange.end}",
			"claim.dateCreated >= #{claimList.dateCreatedRange.begin}",};

	@Observer("archivedClaim")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Claim e) {

		builder.append("\""
				+ (e.getPolicy() != null ? e.getPolicy().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getClaimNumber() != null ? e.getClaimNumber().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getSummary() != null
						? e.getSummary().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getClaimDate() != null ? e.getClaimDate() : "") + "\",");

		builder.append("\""
				+ (e.getClaimAmount() != null ? e.getClaimAmount() : "")
				+ "\",");

		builder.append("\""
				+ (e.getClaimDescription() != null ? e.getClaimDescription()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getStatus() != null ? e.getStatus() : "")
				+ "\",");

		builder.append("\""
				+ (e.getClaimPatient() != null ? e.getClaimPatient()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Policy" + ",");

		builder.append("ClaimNumber" + ",");

		builder.append("Summary" + ",");

		builder.append("ClaimDate" + ",");

		builder.append("ClaimAmount" + ",");

		builder.append("ClaimDescription" + ",");

		builder.append("Status" + ",");

		builder.append("ClaimPatient" + ",");

		builder.append("\r\n");
	}
}
