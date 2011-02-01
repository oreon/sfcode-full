package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.PaidFee;

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

import com.oreon.tapovan.domain.PaidFee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PaidFeeListQueryBase extends BaseQuery<PaidFee, Long> {

	private static final String EJBQL = "select paidFee from PaidFee paidFee";

	protected PaidFee paidFee = new PaidFee();

	public PaidFee getPaidFee() {
		return paidFee;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<PaidFee> getEntityClass() {
		return PaidFee.class;
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
			"paidFee.id = #{paidFeeList.paidFee.id}",

			"paidFee.amount >= #{paidFeeList.amountRange.begin}",
			"paidFee.amount <= #{paidFeeList.amountRange.end}",

			"lower(paidFee.notes) like concat(lower(#{paidFeeList.paidFee.notes}),'%')",

			"paidFee.student.id = #{paidFeeList.paidFee.student.id}",

			"paidFee.gradeFee.id = #{paidFeeList.paidFee.gradeFee.id}",

			"paidFee.dateCreated <= #{paidFeeList.dateCreatedRange.end}",
			"paidFee.dateCreated >= #{paidFeeList.dateCreatedRange.begin}",};

	@Observer("archivedPaidFee")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, PaidFee e) {

		builder.append("\"" + (e.getAmount() != null ? e.getAmount() : "")
				+ "\",");

		builder.append("\"" + (e.getNotes() != null ? e.getNotes() : "")
				+ "\",");

		builder.append("\""
				+ (e.getStudent() != null
						? e.getStudent().getDisplayName()
						: "") + "\",");

		builder.append("\""
				+ (e.getGradeFee() != null
						? e.getGradeFee().getDisplayName()
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Amount" + ",");

		builder.append("Notes" + ",");

		builder.append("Student" + ",");

		builder.append("GradeFee" + ",");

		builder.append("\r\n");
	}
}
