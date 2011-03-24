package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.Policy;

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

import com.pwc.insuranceclaims.quickclaim.Policy;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PolicyListQueryBase extends BaseQuery<Policy, Long> {

	private static final String EJBQL = "select policy from Policy policy";

	protected Policy policy = new Policy();

	public Policy getPolicy() {
		return policy;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Policy> getEntityClass() {
		return Policy.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> premiumRange = new Range<Double>();
	public Range<Double> getPremiumRange() {
		return premiumRange;
	}
	public void setPremium(Range<Double> premiumRange) {
		this.premiumRange = premiumRange;
	}

	private static final String[] RESTRICTIONS = {
			"policy.id = #{policyList.policy.id}",

			"lower(policy.policyNumber) like concat(lower(#{policyList.policy.policyNumber}),'%')",

			"policy.policyType = #{policyList.policy.policyType}",

			"policy.customer.id = #{policyList.policy.customer.id}",

			"policy.premium >= #{policyList.premiumRange.begin}",
			"policy.premium <= #{policyList.premiumRange.end}",

			"policy.dateCreated <= #{policyList.dateCreatedRange.end}",
			"policy.dateCreated >= #{policyList.dateCreatedRange.begin}",};

	public List<Policy> getPolicysByCustomer(
			com.pwc.insuranceclaims.quickclaim.Customer customer) {
		//setMaxResults(10000);
		policy.setCustomer(customer);
		return getResultList();
	}

	@Observer("archivedPolicy")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Policy e) {

		builder.append("\""
				+ (e.getPolicyNumber() != null ? e.getPolicyNumber().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPolicyType() != null ? e.getPolicyType() : "") + "\",");

		builder.append("\""
				+ (e.getCustomer() != null ? e.getCustomer().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getPremium() != null ? e.getPremium() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("PolicyNumber" + ",");

		builder.append("PolicyType" + ",");

		builder.append("Customer" + ",");

		builder.append("Premium" + ",");

		builder.append("\r\n");
	}
}
