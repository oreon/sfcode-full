package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.FeeIncrease;

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

import com.nas.recovery.domain.appraisal.FeeIncrease;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class FeeIncreaseListQueryBase
		extends
			BaseQuery<FeeIncrease, Long> {

	//private static final String EJBQL = "select feeIncrease from FeeIncrease feeIncrease";

	private FeeIncrease feeIncrease = new FeeIncrease();

	private Range<Integer> feeIncrease_feeIncreaseNumberRange = new Range<Integer>();
	public Range<Integer> getFeeIncrease_feeIncreaseNumberRange() {
		return feeIncrease_feeIncreaseNumberRange;
	}
	public void setFeeIncrease_feeIncreaseNumber(
			Range<Integer> feeIncrease_feeIncreaseNumberRange) {
		this.feeIncrease_feeIncreaseNumberRange = feeIncrease_feeIncreaseNumberRange;
	}

	private Range<Integer> feeIncrease_appraisalNumberRange = new Range<Integer>();
	public Range<Integer> getFeeIncrease_appraisalNumberRange() {
		return feeIncrease_appraisalNumberRange;
	}
	public void setFeeIncrease_appraisalNumber(
			Range<Integer> feeIncrease_appraisalNumberRange) {
		this.feeIncrease_appraisalNumberRange = feeIncrease_appraisalNumberRange;
	}

	private Range<Date> feeIncrease_feeIncreaseDateRange = new Range<Date>();
	public Range<Date> getFeeIncrease_feeIncreaseDateRange() {
		return feeIncrease_feeIncreaseDateRange;
	}
	public void setFeeIncrease_feeIncreaseDate(
			Range<Date> feeIncrease_feeIncreaseDateRange) {
		this.feeIncrease_feeIncreaseDateRange = feeIncrease_feeIncreaseDateRange;
	}

	private Range<Double> feeIncrease_amountRange = new Range<Double>();
	public Range<Double> getFeeIncrease_amountRange() {
		return feeIncrease_amountRange;
	}
	public void setFeeIncrease_amount(Range<Double> feeIncrease_amountRange) {
		this.feeIncrease_amountRange = feeIncrease_amountRange;
	}

	private static final String[] RESTRICTIONS = {
			"feeIncrease.id = #{feeIncreaseList.feeIncrease.id}",

			"lower(feeIncrease.feeIncreaseStatus) like concat(lower(#{feeIncreaseList.feeIncrease.feeIncreaseStatus}),'%')",

			"feeIncrease.feeIncreaseNumber >= #{feeIncreaseList.feeIncrease_feeIncreaseNumberRange.begin}",
			"feeIncrease.feeIncreaseNumber <= #{feeIncreaseList.feeIncrease_feeIncreaseNumberRange.end}",

			"feeIncrease.appraisalNumber >= #{feeIncreaseList.feeIncrease_appraisalNumberRange.begin}",
			"feeIncrease.appraisalNumber <= #{feeIncreaseList.feeIncrease_appraisalNumberRange.end}",

			"feeIncrease.feeIncreaseDate >= #{feeIncreaseList.feeIncrease_feeIncreaseDateRange.begin}",
			"feeIncrease.feeIncreaseDate <= #{feeIncreaseList.feeIncrease_feeIncreaseDateRange.end}",

			"feeIncrease.amount >= #{feeIncreaseList.feeIncrease_amountRange.begin}",
			"feeIncrease.amount <= #{feeIncreaseList.feeIncrease_amountRange.end}",

			"lower(feeIncrease.details) like concat(lower(#{feeIncreaseList.feeIncrease.details}),'%')",

			"feeIncrease.dateCreated <= #{feeIncreaseList.dateCreatedRange.end}",
			"feeIncrease.dateCreated >= #{feeIncreaseList.dateCreatedRange.begin}",};

	public FeeIncrease getFeeIncrease() {
		return feeIncrease;
	}

	@Override
	public Class<FeeIncrease> getEntityClass() {
		return FeeIncrease.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedFeeIncrease")
	public void onArchive() {
		refresh();
	}
}
