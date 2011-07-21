package com.oreon.smartsis.web.action.fees;

import com.oreon.smartsis.fees.GradeFeeItem;

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

import com.oreon.smartsis.fees.GradeFeeItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GradeFeeItemListQueryBase
		extends
			BaseQuery<GradeFeeItem, Long> {

	private static final String EJBQL = "select gradeFeeItem from GradeFeeItem gradeFeeItem";

	protected GradeFeeItem gradeFeeItem = new GradeFeeItem();

	public GradeFeeItem getGradeFeeItem() {
		return gradeFeeItem;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<GradeFeeItem> getEntityClass() {
		return GradeFeeItem.class;
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
			"gradeFeeItem.id = #{gradeFeeItemList.gradeFeeItem.id}",

			"gradeFeeItem.monthlyFee.id = #{gradeFeeItemList.gradeFeeItem.monthlyFee.id}",

			"gradeFeeItem.feeItem.id = #{gradeFeeItemList.gradeFeeItem.feeItem.id}",

			"gradeFeeItem.amount >= #{gradeFeeItemList.amountRange.begin}",
			"gradeFeeItem.amount <= #{gradeFeeItemList.amountRange.end}",

			"gradeFeeItem.dateCreated <= #{gradeFeeItemList.dateCreatedRange.end}",
			"gradeFeeItem.dateCreated >= #{gradeFeeItemList.dateCreatedRange.begin}",};

	public List<GradeFeeItem> getGradeFeeItemsByMonthlyFee(
			com.oreon.smartsis.fees.MonthlyFee monthlyFee) {
		//setMaxResults(10000);
		gradeFeeItem.setMonthlyFee(monthlyFee);
		return getResultList();
	}

	@Observer("archivedGradeFeeItem")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, GradeFeeItem e) {

		builder.append("\""
				+ (e.getMonthlyFee() != null ? e.getMonthlyFee()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getFeeItem() != null ? e.getFeeItem().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getAmount() != null ? e.getAmount() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("MonthlyFee" + ",");

		builder.append("FeeItem" + ",");

		builder.append("Amount" + ",");

		builder.append("\r\n");
	}
}
