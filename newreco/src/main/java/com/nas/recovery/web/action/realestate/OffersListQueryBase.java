package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Offers;

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

import com.nas.recovery.domain.realestate.Offers;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class OffersListQueryBase extends BaseQuery<Offers, Long> {

	//private static final String EJBQL = "select offers from Offers offers";

	private Offers offers = new Offers();

	private Range<Date> offers_offerDateRange = new Range<Date>();
	public Range<Date> getOffers_offerDateRange() {
		return offers_offerDateRange;
	}
	public void setOffers_offerDate(Range<Date> offers_offerDateRange) {
		this.offers_offerDateRange = offers_offerDateRange;
	}

	private Range<Double> offers_amountRange = new Range<Double>();
	public Range<Double> getOffers_amountRange() {
		return offers_amountRange;
	}
	public void setOffers_amount(Range<Double> offers_amountRange) {
		this.offers_amountRange = offers_amountRange;
	}

	private Range<Double> offers_signBackAmountRange = new Range<Double>();
	public Range<Double> getOffers_signBackAmountRange() {
		return offers_signBackAmountRange;
	}
	public void setOffers_signBackAmount(
			Range<Double> offers_signBackAmountRange) {
		this.offers_signBackAmountRange = offers_signBackAmountRange;
	}

	private Range<Date> offers_conditionExpiryRange = new Range<Date>();
	public Range<Date> getOffers_conditionExpiryRange() {
		return offers_conditionExpiryRange;
	}
	public void setOffers_conditionExpiry(
			Range<Date> offers_conditionExpiryRange) {
		this.offers_conditionExpiryRange = offers_conditionExpiryRange;
	}

	private Range<Date> offers_closingDateRange = new Range<Date>();
	public Range<Date> getOffers_closingDateRange() {
		return offers_closingDateRange;
	}
	public void setOffers_closingDate(Range<Date> offers_closingDateRange) {
		this.offers_closingDateRange = offers_closingDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"offers.id = #{offersList.offers.id}",

			"offers.offerDate >= #{offersList.offers_offerDateRange.begin}",
			"offers.offerDate <= #{offersList.offers_offerDateRange.end}",

			"lower(offers.purchaser) like concat(lower(#{offersList.offers.purchaser}),'%')",

			"offers.amount >= #{offersList.offers_amountRange.begin}",
			"offers.amount <= #{offersList.offers_amountRange.end}",

			"offers.condition = #{offersList.offers.condition}",

			"offers.status = #{offersList.offers.status}",

			"offers.signBackAmount >= #{offersList.offers_signBackAmountRange.begin}",
			"offers.signBackAmount <= #{offersList.offers_signBackAmountRange.end}",

			"offers.conditionExpiry >= #{offersList.offers_conditionExpiryRange.begin}",
			"offers.conditionExpiry <= #{offersList.offers_conditionExpiryRange.end}",

			"offers.realEstateListing = #{offersList.offers.realEstateListing}",

			"offers.closingDate >= #{offersList.offers_closingDateRange.begin}",
			"offers.closingDate <= #{offersList.offers_closingDateRange.end}",

			"offers.comments = #{offersList.offers.comments}",

			"offers.dateCreated <= #{offersList.dateCreatedRange.end}",
			"offers.dateCreated >= #{offersList.dateCreatedRange.begin}",};

	public Offers getOffers() {
		return offers;
	}

	@Override
	public Class<Offers> getEntityClass() {
		return Offers.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedOffers")
	public void onArchive() {
		refresh();
	}
}
