package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.GradeFee;

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

import com.oreon.tapovan.domain.GradeFee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GradeFeeListQueryBase extends BaseQuery<GradeFee, Long> {

	private static final String EJBQL = "select gradeFee from GradeFee gradeFee";

	protected GradeFee gradeFee = new GradeFee();

	public GradeFee getGradeFee() {
		return gradeFee;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<GradeFee> getEntityClass() {
		return GradeFee.class;
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
			"gradeFee.id = #{gradeFeeList.gradeFee.id}",

			"gradeFee.grade.id = #{gradeFeeList.gradeFee.grade.id}",

			"gradeFee.fee.id = #{gradeFeeList.gradeFee.fee.id}",

			"gradeFee.amount >= #{gradeFeeList.amountRange.begin}",
			"gradeFee.amount <= #{gradeFeeList.amountRange.end}",

			"gradeFee.dateCreated <= #{gradeFeeList.dateCreatedRange.end}",
			"gradeFee.dateCreated >= #{gradeFeeList.dateCreatedRange.begin}",};

	public List<GradeFee> getGradeFeesByGrade(
			com.oreon.tapovan.domain.Grade grade) {
		//setMaxResults(10000);
		gradeFee.setGrade(grade);
		return getResultList();
	}

	@Observer("archivedGradeFee")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, GradeFee e) {

		builder.append("\""
				+ (e.getGrade() != null ? e.getGrade().getDisplayName() : "")
				+ "\",");

		builder.append("\""
				+ (e.getFee() != null ? e.getFee().getDisplayName() : "")
				+ "\",");

		builder.append("\"" + (e.getAmount() != null ? e.getAmount() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Grade" + ",");

		builder.append("Fee" + ",");

		builder.append("Amount" + ",");

		builder.append("\r\n");
	}
}
