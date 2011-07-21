package com.oreon.smartsis.web.action.fees;

import com.oreon.smartsis.fees.MonthlyFee;

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

import com.oreon.smartsis.fees.MonthlyFee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MonthlyFeeListQueryBase
		extends
			BaseQuery<MonthlyFee, Long> {

	private static final String EJBQL = "select monthlyFee from MonthlyFee monthlyFee";

	protected MonthlyFee monthlyFee = new MonthlyFee();

	public MonthlyFee getMonthlyFee() {
		return monthlyFee;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MonthlyFee> getEntityClass() {
		return MonthlyFee.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> totalRange = new Range<Double>();
	public Range<Double> getTotalRange() {
		return totalRange;
	}
	public void setTotal(Range<Double> totalRange) {
		this.totalRange = totalRange;
	}

	private static final String[] RESTRICTIONS = {
			"monthlyFee.id = #{monthlyFeeList.monthlyFee.id}",

			"monthlyFee.grade.id = #{monthlyFeeList.monthlyFee.grade.id}",

			"monthlyFee.month = #{monthlyFeeList.monthlyFee.month}",

			"monthlyFee.total >= #{monthlyFeeList.totalRange.begin}",
			"monthlyFee.total <= #{monthlyFeeList.totalRange.end}",

			"monthlyFee.dateCreated <= #{monthlyFeeList.dateCreatedRange.end}",
			"monthlyFee.dateCreated >= #{monthlyFeeList.dateCreatedRange.begin}",};

	@Observer("archivedMonthlyFee")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MonthlyFee e) {

		builder.append("\""
				+ (e.getGrade() != null ? e.getGrade().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getMonth() != null ? e.getMonth() : "")
				+ "\",");

		builder.append("\"" + (e.getTotal() != null ? e.getTotal() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Grade" + ",");

		builder.append("Month" + ",");

		builder.append("Total" + ",");

		builder.append("\r\n");
	}
}
