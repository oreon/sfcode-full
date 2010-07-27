package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.TitleSummary;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.nas.recovery.domain.legal.TitleSummary;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class TitleSummaryListQueryBase
		extends
			BaseQuery<TitleSummary, Long> {

	//private static final String EJBQL = "select titleSummary from TitleSummary titleSummary";

	private TitleSummary titleSummary = new TitleSummary();

	private Range<Integer> titleSummary_legalNumberRange = new Range<Integer>();
	public Range<Integer> getTitleSummary_legalNumberRange() {
		return titleSummary_legalNumberRange;
	}
	public void setTitleSummary_legalNumber(
			Range<Integer> titleSummary_legalNumberRange) {
		this.titleSummary_legalNumberRange = titleSummary_legalNumberRange;
	}

	private Range<Double> titleSummary_amountRange = new Range<Double>();
	public Range<Double> getTitleSummary_amountRange() {
		return titleSummary_amountRange;
	}
	public void setTitleSummary_amount(Range<Double> titleSummary_amountRange) {
		this.titleSummary_amountRange = titleSummary_amountRange;
	}

	private static final String[] RESTRICTIONS = {
			"titleSummary.id = #{titleSummaryList.titleSummary.id}",

			"titleSummary.legalNumber >= #{titleSummaryList.titleSummary_legalNumberRange.begin}",
			"titleSummary.legalNumber <= #{titleSummaryList.titleSummary_legalNumberRange.end}",

			"lower(titleSummary.chargeHolder) like concat(lower(#{titleSummaryList.titleSummary.chargeHolder}),'%')",

			"lower(titleSummary.priority) like concat(lower(#{titleSummaryList.titleSummary.priority}),'%')",

			"lower(titleSummary.description) like concat(lower(#{titleSummaryList.titleSummary.description}),'%')",

			"titleSummary.amount >= #{titleSummaryList.titleSummary_amountRange.begin}",
			"titleSummary.amount <= #{titleSummaryList.titleSummary_amountRange.end}",

			"titleSummary.dateCreated <= #{titleSummaryList.dateCreatedRange.end}",
			"titleSummary.dateCreated >= #{titleSummaryList.dateCreatedRange.begin}",};

	public TitleSummary getTitleSummary() {
		return titleSummary;
	}

	@Override
	public Class<TitleSummary> getEntityClass() {
		return TitleSummary.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedTitleSummary")
	public void onArchive() {
		refresh();
	}
}
