package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.AppraisalHistory;

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

import com.nas.recovery.domain.appraisal.AppraisalHistory;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AppraisalHistoryListQueryBase
		extends
			BaseQuery<AppraisalHistory, Long> {

	//private static final String EJBQL = "select appraisalHistory from AppraisalHistory appraisalHistory";

	private AppraisalHistory appraisalHistory = new AppraisalHistory();

	private Range<Integer> appraisalHistory_requestNumberRange = new Range<Integer>();
	public Range<Integer> getAppraisalHistory_requestNumberRange() {
		return appraisalHistory_requestNumberRange;
	}
	public void setAppraisalHistory_requestNumber(
			Range<Integer> appraisalHistory_requestNumberRange) {
		this.appraisalHistory_requestNumberRange = appraisalHistory_requestNumberRange;
	}

	private Range<Double> appraisalHistory_amountRange = new Range<Double>();
	public Range<Double> getAppraisalHistory_amountRange() {
		return appraisalHistory_amountRange;
	}
	public void setAppraisalHistory_amount(
			Range<Double> appraisalHistory_amountRange) {
		this.appraisalHistory_amountRange = appraisalHistory_amountRange;
	}

	private Range<Date> appraisalHistory_requestDateRange = new Range<Date>();
	public Range<Date> getAppraisalHistory_requestDateRange() {
		return appraisalHistory_requestDateRange;
	}
	public void setAppraisalHistory_requestDate(
			Range<Date> appraisalHistory_requestDateRange) {
		this.appraisalHistory_requestDateRange = appraisalHistory_requestDateRange;
	}

	private Range<Integer> appraisalHistory_appraisalNumberRange = new Range<Integer>();
	public Range<Integer> getAppraisalHistory_appraisalNumberRange() {
		return appraisalHistory_appraisalNumberRange;
	}
	public void setAppraisalHistory_appraisalNumber(
			Range<Integer> appraisalHistory_appraisalNumberRange) {
		this.appraisalHistory_appraisalNumberRange = appraisalHistory_appraisalNumberRange;
	}

	private static final String[] RESTRICTIONS = {
			"appraisalHistory.id = #{appraisalHistoryList.appraisalHistory.id}",

			"lower(appraisalHistory.notes) like concat(lower(#{appraisalHistoryList.appraisalHistory.notes}),'%')",

			"appraisalHistory.requestNumber >= #{appraisalHistoryList.appraisalHistory_requestNumberRange.begin}",
			"appraisalHistory.requestNumber <= #{appraisalHistoryList.appraisalHistory_requestNumberRange.end}",

			"appraisalHistory.amount >= #{appraisalHistoryList.appraisalHistory_amountRange.begin}",
			"appraisalHistory.amount <= #{appraisalHistoryList.appraisalHistory_amountRange.end}",

			"appraisalHistory.requestDate >= #{appraisalHistoryList.appraisalHistory_requestDateRange.begin}",
			"appraisalHistory.requestDate <= #{appraisalHistoryList.appraisalHistory_requestDateRange.end}",

			"lower(appraisalHistory.type) like concat(lower(#{appraisalHistoryList.appraisalHistory.type}),'%')",

			"appraisalHistory.asImproved = #{appraisalHistoryList.appraisalHistory.asImproved}",

			"appraisalHistory.reviewed = #{appraisalHistoryList.appraisalHistory.reviewed}",

			"lower(appraisalHistory.appraiser) like concat(lower(#{appraisalHistoryList.appraisalHistory.appraiser}),'%')",

			"appraisalHistory.appraisalNumber >= #{appraisalHistoryList.appraisalHistory_appraisalNumberRange.begin}",
			"appraisalHistory.appraisalNumber <= #{appraisalHistoryList.appraisalHistory_appraisalNumberRange.end}",

			"appraisalHistory.dateCreated <= #{appraisalHistoryList.dateCreatedRange.end}",
			"appraisalHistory.dateCreated >= #{appraisalHistoryList.dateCreatedRange.begin}",};

	public AppraisalHistory getAppraisalHistory() {
		return appraisalHistory;
	}

	@Override
	public Class<AppraisalHistory> getEntityClass() {
		return AppraisalHistory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedAppraisalHistory")
	public void onArchive() {
		refresh();
	}
}
