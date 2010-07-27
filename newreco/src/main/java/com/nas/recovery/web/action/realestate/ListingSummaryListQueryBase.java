package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.ListingSummary;

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

import com.nas.recovery.domain.realestate.ListingSummary;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ListingSummaryListQueryBase
		extends
			BaseQuery<ListingSummary, Long> {

	//private static final String EJBQL = "select listingSummary from ListingSummary listingSummary";

	private ListingSummary listingSummary = new ListingSummary();

	private Range<Date> listingSummary_expiryDateRange = new Range<Date>();
	public Range<Date> getListingSummary_expiryDateRange() {
		return listingSummary_expiryDateRange;
	}
	public void setListingSummary_expiryDate(
			Range<Date> listingSummary_expiryDateRange) {
		this.listingSummary_expiryDateRange = listingSummary_expiryDateRange;
	}

	private Range<Date> listingSummary_dateListedRange = new Range<Date>();
	public Range<Date> getListingSummary_dateListedRange() {
		return listingSummary_dateListedRange;
	}
	public void setListingSummary_dateListed(
			Range<Date> listingSummary_dateListedRange) {
		this.listingSummary_dateListedRange = listingSummary_dateListedRange;
	}

	private Range<Double> listingSummary_amountRange = new Range<Double>();
	public Range<Double> getListingSummary_amountRange() {
		return listingSummary_amountRange;
	}
	public void setListingSummary_amount(
			Range<Double> listingSummary_amountRange) {
		this.listingSummary_amountRange = listingSummary_amountRange;
	}

	private static final String[] RESTRICTIONS = {
			"listingSummary.id = #{listingSummaryList.listingSummary.id}",

			"lower(listingSummary.report) like concat(lower(#{listingSummaryList.listingSummary.report}),'%')",

			"listingSummary.expiryDate >= #{listingSummaryList.listingSummary_expiryDateRange.begin}",
			"listingSummary.expiryDate <= #{listingSummaryList.listingSummary_expiryDateRange.end}",

			"lower(listingSummary.agent) like concat(lower(#{listingSummaryList.listingSummary.agent}),'%')",

			"listingSummary.dateListed >= #{listingSummaryList.listingSummary_dateListedRange.begin}",
			"listingSummary.dateListed <= #{listingSummaryList.listingSummary_dateListedRange.end}",

			"listingSummary.amount >= #{listingSummaryList.listingSummary_amountRange.begin}",
			"listingSummary.amount <= #{listingSummaryList.listingSummary_amountRange.end}",

			"listingSummary.dateCreated <= #{listingSummaryList.dateCreatedRange.end}",
			"listingSummary.dateCreated >= #{listingSummaryList.dateCreatedRange.begin}",};

	public ListingSummary getListingSummary() {
		return listingSummary;
	}

	@Override
	public Class<ListingSummary> getEntityClass() {
		return ListingSummary.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedListingSummary")
	public void onArchive() {
		refresh();
	}
}
