package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.LoanData;

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

import com.nas.recovery.domain.loan.LoanData;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class LoanDataListQueryBase extends BaseQuery<LoanData, Long> {

	//private static final String EJBQL = "select loanData from LoanData loanData";

	private LoanData loanData = new LoanData();

	private Range<Double> loanData_feesRange = new Range<Double>();
	public Range<Double> getLoanData_feesRange() {
		return loanData_feesRange;
	}
	public void setLoanData_fees(Range<Double> loanData_feesRange) {
		this.loanData_feesRange = loanData_feesRange;
	}

	private Range<Double> loanData_originalAppraisedValueRange = new Range<Double>();
	public Range<Double> getLoanData_originalAppraisedValueRange() {
		return loanData_originalAppraisedValueRange;
	}
	public void setLoanData_originalAppraisedValue(
			Range<Double> loanData_originalAppraisedValueRange) {
		this.loanData_originalAppraisedValueRange = loanData_originalAppraisedValueRange;
	}

	private Range<Integer> loanData_mortgageNumberRange = new Range<Integer>();
	public Range<Integer> getLoanData_mortgageNumberRange() {
		return loanData_mortgageNumberRange;
	}
	public void setLoanData_mortgageNumber(
			Range<Integer> loanData_mortgageNumberRange) {
		this.loanData_mortgageNumberRange = loanData_mortgageNumberRange;
	}

	private Range<Double> loanData_interestRateRange = new Range<Double>();
	public Range<Double> getLoanData_interestRateRange() {
		return loanData_interestRateRange;
	}
	public void setLoanData_interestRate(
			Range<Double> loanData_interestRateRange) {
		this.loanData_interestRateRange = loanData_interestRateRange;
	}

	private Range<Double> loanData_arrearsRange = new Range<Double>();
	public Range<Double> getLoanData_arrearsRange() {
		return loanData_arrearsRange;
	}
	public void setLoanData_arrears(Range<Double> loanData_arrearsRange) {
		this.loanData_arrearsRange = loanData_arrearsRange;
	}

	private Range<Date> loanData_fundingDateRange = new Range<Date>();
	public Range<Date> getLoanData_fundingDateRange() {
		return loanData_fundingDateRange;
	}
	public void setLoanData_fundingDate(Range<Date> loanData_fundingDateRange) {
		this.loanData_fundingDateRange = loanData_fundingDateRange;
	}

	private Range<Double> loanData_totalOutstandingRange = new Range<Double>();
	public Range<Double> getLoanData_totalOutstandingRange() {
		return loanData_totalOutstandingRange;
	}
	public void setLoanData_totalOutstanding(
			Range<Double> loanData_totalOutstandingRange) {
		this.loanData_totalOutstandingRange = loanData_totalOutstandingRange;
	}

	private Range<Double> loanData_mortgageBalanceRange = new Range<Double>();
	public Range<Double> getLoanData_mortgageBalanceRange() {
		return loanData_mortgageBalanceRange;
	}
	public void setLoanData_mortgageBalance(
			Range<Double> loanData_mortgageBalanceRange) {
		this.loanData_mortgageBalanceRange = loanData_mortgageBalanceRange;
	}

	private Range<Date> loanData_maturityDateRange = new Range<Date>();
	public Range<Date> getLoanData_maturityDateRange() {
		return loanData_maturityDateRange;
	}
	public void setLoanData_maturityDate(Range<Date> loanData_maturityDateRange) {
		this.loanData_maturityDateRange = loanData_maturityDateRange;
	}

	private Range<Double> loanData_taxesRange = new Range<Double>();
	public Range<Double> getLoanData_taxesRange() {
		return loanData_taxesRange;
	}
	public void setLoanData_taxes(Range<Double> loanData_taxesRange) {
		this.loanData_taxesRange = loanData_taxesRange;
	}

	private static final String[] RESTRICTIONS = {
			"loanData.id = #{loanDataList.loanData.id}",

			"loanData.fees >= #{loanDataList.loanData_feesRange.begin}",
			"loanData.fees <= #{loanDataList.loanData_feesRange.end}",

			"loanData.originalAppraisedValue >= #{loanDataList.loanData_originalAppraisedValueRange.begin}",
			"loanData.originalAppraisedValue <= #{loanDataList.loanData_originalAppraisedValueRange.end}",

			"lower(loanData.taxPortion) like concat(lower(#{loanDataList.loanData.taxPortion}),'%')",

			"loanData.mortgageNumber >= #{loanDataList.loanData_mortgageNumberRange.begin}",
			"loanData.mortgageNumber <= #{loanDataList.loanData_mortgageNumberRange.end}",

			"loanData.interestRate >= #{loanDataList.loanData_interestRateRange.begin}",
			"loanData.interestRate <= #{loanDataList.loanData_interestRateRange.end}",

			"loanData.arrears >= #{loanDataList.loanData_arrearsRange.begin}",
			"loanData.arrears <= #{loanDataList.loanData_arrearsRange.end}",

			"loanData.fundingDate >= #{loanDataList.loanData_fundingDateRange.begin}",
			"loanData.fundingDate <= #{loanDataList.loanData_fundingDateRange.end}",

			"loanData.condo = #{loanDataList.loanData.condo}",

			"loanData.totalOutstanding >= #{loanDataList.loanData_totalOutstandingRange.begin}",
			"loanData.totalOutstanding <= #{loanDataList.loanData_totalOutstandingRange.end}",

			"loanData.mortgageBalance >= #{loanDataList.loanData_mortgageBalanceRange.begin}",
			"loanData.mortgageBalance <= #{loanDataList.loanData_mortgageBalanceRange.end}",

			"loanData.maturityDate >= #{loanDataList.loanData_maturityDateRange.begin}",
			"loanData.maturityDate <= #{loanDataList.loanData_maturityDateRange.end}",

			"lower(loanData.repaymentHistory) like concat(lower(#{loanDataList.loanData.repaymentHistory}),'%')",

			"lower(loanData.originalPurposeTransaction) like concat(lower(#{loanDataList.loanData.originalPurposeTransaction}),'%')",

			"loanData.taxes >= #{loanDataList.loanData_taxesRange.begin}",
			"loanData.taxes <= #{loanDataList.loanData_taxesRange.end}",

			"loanData.rentalProperty = #{loanDataList.loanData.rentalProperty}",

			"loanData.lender = #{loanDataList.loanData.lender}",

			"loanData.mortgageInsurer = #{loanDataList.loanData.mortgageInsurer}",

			"loanData.titleInsurer = #{loanDataList.loanData.titleInsurer}",

			"loanData.broker = #{loanDataList.loanData.broker}",

			"loanData.underwriter = #{loanDataList.loanData.underwriter}",

			"loanData.dateCreated <= #{loanDataList.dateCreatedRange.end}",
			"loanData.dateCreated >= #{loanDataList.dateCreatedRange.begin}",};

	public LoanData getLoanData() {
		return loanData;
	}

	@Override
	public Class<LoanData> getEntityClass() {
		return LoanData.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedLoanData")
	public void onArchive() {
		refresh();
	}
}
