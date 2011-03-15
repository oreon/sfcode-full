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

	private static final String[] RESTRICTIONS = {
			"claim.id = #{claimList.claim.id}",

			"claim.policy.id = #{claimList.claim.policy.id}",

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

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Policy" + ",");

		builder.append("\r\n");
	}
}
