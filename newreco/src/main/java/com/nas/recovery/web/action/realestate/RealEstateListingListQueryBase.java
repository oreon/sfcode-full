package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateListing;

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

import com.nas.recovery.domain.realestate.RealEstateListing;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class RealEstateListingListQueryBase
		extends
			BaseQuery<RealEstateListing, Long> {

	//private static final String EJBQL = "select realEstateListing from RealEstateListing realEstateListing";

	private RealEstateListing realEstateListing = new RealEstateListing();

	private Range<Date> realEstateListing_dateListedRange = new Range<Date>();
	public Range<Date> getRealEstateListing_dateListedRange() {
		return realEstateListing_dateListedRange;
	}
	public void setRealEstateListing_dateListed(
			Range<Date> realEstateListing_dateListedRange) {
		this.realEstateListing_dateListedRange = realEstateListing_dateListedRange;
	}

	private Range<Date> realEstateListing_expiryDateRange = new Range<Date>();
	public Range<Date> getRealEstateListing_expiryDateRange() {
		return realEstateListing_expiryDateRange;
	}
	public void setRealEstateListing_expiryDate(
			Range<Date> realEstateListing_expiryDateRange) {
		this.realEstateListing_expiryDateRange = realEstateListing_expiryDateRange;
	}

	private Range<Double> realEstateListing_listPriceRange = new Range<Double>();
	public Range<Double> getRealEstateListing_listPriceRange() {
		return realEstateListing_listPriceRange;
	}
	public void setRealEstateListing_listPrice(
			Range<Double> realEstateListing_listPriceRange) {
		this.realEstateListing_listPriceRange = realEstateListing_listPriceRange;
	}

	private Range<Double> realEstateListing_minSalePriceRange = new Range<Double>();
	public Range<Double> getRealEstateListing_minSalePriceRange() {
		return realEstateListing_minSalePriceRange;
	}
	public void setRealEstateListing_minSalePrice(
			Range<Double> realEstateListing_minSalePriceRange) {
		this.realEstateListing_minSalePriceRange = realEstateListing_minSalePriceRange;
	}

	private Range<Integer> realEstateListing_mlsNumberRange = new Range<Integer>();
	public Range<Integer> getRealEstateListing_mlsNumberRange() {
		return realEstateListing_mlsNumberRange;
	}
	public void setRealEstateListing_mlsNumber(
			Range<Integer> realEstateListing_mlsNumberRange) {
		this.realEstateListing_mlsNumberRange = realEstateListing_mlsNumberRange;
	}

	private Range<Integer> realEstateListing_daysOnMarketRange = new Range<Integer>();
	public Range<Integer> getRealEstateListing_daysOnMarketRange() {
		return realEstateListing_daysOnMarketRange;
	}
	public void setRealEstateListing_daysOnMarket(
			Range<Integer> realEstateListing_daysOnMarketRange) {
		this.realEstateListing_daysOnMarketRange = realEstateListing_daysOnMarketRange;
	}

	private Range<Double> realEstateListing_propertyTaxesRange = new Range<Double>();
	public Range<Double> getRealEstateListing_propertyTaxesRange() {
		return realEstateListing_propertyTaxesRange;
	}
	public void setRealEstateListing_propertyTaxes(
			Range<Double> realEstateListing_propertyTaxesRange) {
		this.realEstateListing_propertyTaxesRange = realEstateListing_propertyTaxesRange;
	}

	private Range<Double> realEstateListing_condoFeesRange = new Range<Double>();
	public Range<Double> getRealEstateListing_condoFeesRange() {
		return realEstateListing_condoFeesRange;
	}
	public void setRealEstateListing_condoFees(
			Range<Double> realEstateListing_condoFeesRange) {
		this.realEstateListing_condoFeesRange = realEstateListing_condoFeesRange;
	}

	private Range<Double> realEstateListing_costPerDiemRange = new Range<Double>();
	public Range<Double> getRealEstateListing_costPerDiemRange() {
		return realEstateListing_costPerDiemRange;
	}
	public void setRealEstateListing_costPerDiem(
			Range<Double> realEstateListing_costPerDiemRange) {
		this.realEstateListing_costPerDiemRange = realEstateListing_costPerDiemRange;
	}

	private Range<Integer> realEstateListing_lockBoxRange = new Range<Integer>();
	public Range<Integer> getRealEstateListing_lockBoxRange() {
		return realEstateListing_lockBoxRange;
	}
	public void setRealEstateListing_lockBox(
			Range<Integer> realEstateListing_lockBoxRange) {
		this.realEstateListing_lockBoxRange = realEstateListing_lockBoxRange;
	}

	private Range<Double> realEstateListing_soldPriceRange = new Range<Double>();
	public Range<Double> getRealEstateListing_soldPriceRange() {
		return realEstateListing_soldPriceRange;
	}
	public void setRealEstateListing_soldPrice(
			Range<Double> realEstateListing_soldPriceRange) {
		this.realEstateListing_soldPriceRange = realEstateListing_soldPriceRange;
	}

	private Range<Integer> realEstateListing_realEstateNumberRange = new Range<Integer>();
	public Range<Integer> getRealEstateListing_realEstateNumberRange() {
		return realEstateListing_realEstateNumberRange;
	}
	public void setRealEstateListing_realEstateNumber(
			Range<Integer> realEstateListing_realEstateNumberRange) {
		this.realEstateListing_realEstateNumberRange = realEstateListing_realEstateNumberRange;
	}

	private Range<Double> realEstateListing_commissionRange = new Range<Double>();
	public Range<Double> getRealEstateListing_commissionRange() {
		return realEstateListing_commissionRange;
	}
	public void setRealEstateListing_commission(
			Range<Double> realEstateListing_commissionRange) {
		this.realEstateListing_commissionRange = realEstateListing_commissionRange;
	}

	private Range<Integer> realEstateListing_mortgageNumberRange = new Range<Integer>();
	public Range<Integer> getRealEstateListing_mortgageNumberRange() {
		return realEstateListing_mortgageNumberRange;
	}
	public void setRealEstateListing_mortgageNumber(
			Range<Integer> realEstateListing_mortgageNumberRange) {
		this.realEstateListing_mortgageNumberRange = realEstateListing_mortgageNumberRange;
	}

	private Range<Date> realEstateListing_soldDateRange = new Range<Date>();
	public Range<Date> getRealEstateListing_soldDateRange() {
		return realEstateListing_soldDateRange;
	}
	public void setRealEstateListing_soldDate(
			Range<Date> realEstateListing_soldDateRange) {
		this.realEstateListing_soldDateRange = realEstateListing_soldDateRange;
	}

	private Range<Double> realEstateListing_depositRange = new Range<Double>();
	public Range<Double> getRealEstateListing_depositRange() {
		return realEstateListing_depositRange;
	}
	public void setRealEstateListing_deposit(
			Range<Double> realEstateListing_depositRange) {
		this.realEstateListing_depositRange = realEstateListing_depositRange;
	}

	private static final String[] RESTRICTIONS = {
			"realEstateListing.id = #{realEstateListingList.realEstateListing.id}",

			"realEstateListing.dateListed >= #{realEstateListingList.realEstateListing_dateListedRange.begin}",
			"realEstateListing.dateListed <= #{realEstateListingList.realEstateListing_dateListedRange.end}",

			"realEstateListing.expiryDate >= #{realEstateListingList.realEstateListing_expiryDateRange.begin}",
			"realEstateListing.expiryDate <= #{realEstateListingList.realEstateListing_expiryDateRange.end}",

			"realEstateListing.listPrice >= #{realEstateListingList.realEstateListing_listPriceRange.begin}",
			"realEstateListing.listPrice <= #{realEstateListingList.realEstateListing_listPriceRange.end}",

			"realEstateListing.minSalePrice >= #{realEstateListingList.realEstateListing_minSalePriceRange.begin}",
			"realEstateListing.minSalePrice <= #{realEstateListingList.realEstateListing_minSalePriceRange.end}",

			"realEstateListing.mlsNumber >= #{realEstateListingList.realEstateListing_mlsNumberRange.begin}",
			"realEstateListing.mlsNumber <= #{realEstateListingList.realEstateListing_mlsNumberRange.end}",

			"realEstateListing.daysOnMarket >= #{realEstateListingList.realEstateListing_daysOnMarketRange.begin}",
			"realEstateListing.daysOnMarket <= #{realEstateListingList.realEstateListing_daysOnMarketRange.end}",

			"realEstateListing.propertyTaxes >= #{realEstateListingList.realEstateListing_propertyTaxesRange.begin}",
			"realEstateListing.propertyTaxes <= #{realEstateListingList.realEstateListing_propertyTaxesRange.end}",

			"realEstateListing.condoFees >= #{realEstateListingList.realEstateListing_condoFeesRange.begin}",
			"realEstateListing.condoFees <= #{realEstateListingList.realEstateListing_condoFeesRange.end}",

			"realEstateListing.costPerDiem >= #{realEstateListingList.realEstateListing_costPerDiemRange.begin}",
			"realEstateListing.costPerDiem <= #{realEstateListingList.realEstateListing_costPerDiemRange.end}",

			"realEstateListing.lockBox >= #{realEstateListingList.realEstateListing_lockBoxRange.begin}",
			"realEstateListing.lockBox <= #{realEstateListingList.realEstateListing_lockBoxRange.end}",

			"realEstateListing.soldPrice >= #{realEstateListingList.realEstateListing_soldPriceRange.begin}",
			"realEstateListing.soldPrice <= #{realEstateListingList.realEstateListing_soldPriceRange.end}",

			"realEstateListing.ownerOccupied = #{realEstateListingList.realEstateListing.ownerOccupied}",

			"realEstateListing.vacant = #{realEstateListingList.realEstateListing.vacant}",

			"realEstateListing.tenanted = #{realEstateListingList.realEstateListing.tenanted}",

			"realEstateListing.mlsComments = #{realEstateListingList.realEstateListing.mlsComments}",

			"realEstateListing.realEstateNumber >= #{realEstateListingList.realEstateListing_realEstateNumberRange.begin}",
			"realEstateListing.realEstateNumber <= #{realEstateListingList.realEstateListing_realEstateNumberRange.end}",

			"lower(realEstateListing.cmaOrdered) like concat(lower(#{realEstateListingList.realEstateListing.cmaOrdered}),'%')",

			"lower(realEstateListing.occupied) like concat(lower(#{realEstateListingList.realEstateListing.occupied}),'%')",

			"realEstateListing.commission >= #{realEstateListingList.realEstateListing_commissionRange.begin}",
			"realEstateListing.commission <= #{realEstateListingList.realEstateListing_commissionRange.end}",

			"realEstateListing.realEstateBoard = #{realEstateListingList.realEstateListing.realEstateBoard}",

			"realEstateListing.realEstateProperty = #{realEstateListingList.realEstateListing.realEstateProperty}",

			"realEstateListing.master = #{realEstateListingList.realEstateListing.master}",

			"realEstateListing.subagent = #{realEstateListingList.realEstateListing.subagent}",

			"realEstateListing.mortgageNumber >= #{realEstateListingList.realEstateListing_mortgageNumberRange.begin}",
			"realEstateListing.mortgageNumber <= #{realEstateListingList.realEstateListing_mortgageNumberRange.end}",

			"realEstateListing.soldDate >= #{realEstateListingList.realEstateListing_soldDateRange.begin}",
			"realEstateListing.soldDate <= #{realEstateListingList.realEstateListing_soldDateRange.end}",

			"realEstateListing.vtbOffered = #{realEstateListingList.realEstateListing.vtbOffered}",

			"realEstateListing.vtbAccepted = #{realEstateListingList.realEstateListing.vtbAccepted}",

			"realEstateListing.deposit >= #{realEstateListingList.realEstateListing_depositRange.begin}",
			"realEstateListing.deposit <= #{realEstateListingList.realEstateListing_depositRange.end}",

			"realEstateListing.sale = #{realEstateListingList.realEstateListing.sale}",

			"realEstateListing.dateCreated <= #{realEstateListingList.dateCreatedRange.end}",
			"realEstateListing.dateCreated >= #{realEstateListingList.dateCreatedRange.begin}",};

	public RealEstateListing getRealEstateListing() {
		return realEstateListing;
	}

	@Override
	public Class<RealEstateListing> getEntityClass() {
		return RealEstateListing.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedRealEstateListing")
	public void onArchive() {
		refresh();
	}
}
