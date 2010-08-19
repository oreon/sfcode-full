package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Offer;

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

import com.nas.recovery.domain.realestate.Offer;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class OfferListQueryBase extends BaseQuery<Offer, Long> {

	//private static final String EJBQL = "select offer from Offer offer";

	private Offer offer = new Offer();

	private Range<Date> offer_offerDateRange = new Range<Date>();
	public Range<Date> getOffer_offerDateRange() {
		return offer_offerDateRange;
	}
	public void setOffer_offerDate(Range<Date> offer_offerDateRange) {
		this.offer_offerDateRange = offer_offerDateRange;
	}

	private Range<Double> offer_amountRange = new Range<Double>();
	public Range<Double> getOffer_amountRange() {
		return offer_amountRange;
	}
	public void setOffer_amount(Range<Double> offer_amountRange) {
		this.offer_amountRange = offer_amountRange;
	}

	private Range<Double> offer_signBackAmountRange = new Range<Double>();
	public Range<Double> getOffer_signBackAmountRange() {
		return offer_signBackAmountRange;
	}
	public void setOffer_signBackAmount(Range<Double> offer_signBackAmountRange) {
		this.offer_signBackAmountRange = offer_signBackAmountRange;
	}

	private Range<Date> offer_conditionExpiryRange = new Range<Date>();
	public Range<Date> getOffer_conditionExpiryRange() {
		return offer_conditionExpiryRange;
	}
	public void setOffer_conditionExpiry(Range<Date> offer_conditionExpiryRange) {
		this.offer_conditionExpiryRange = offer_conditionExpiryRange;
	}

	private Range<Date> offer_closingDateRange = new Range<Date>();
	public Range<Date> getOffer_closingDateRange() {
		return offer_closingDateRange;
	}
	public void setOffer_closingDate(Range<Date> offer_closingDateRange) {
		this.offer_closingDateRange = offer_closingDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"offer.id = #{offerList.offer.id}",

			"offer.offerDate >= #{offerList.offer_offerDateRange.begin}",
			"offer.offerDate <= #{offerList.offer_offerDateRange.end}",

			"lower(offer.purchaser) like concat(lower(#{offerList.offer.purchaser}),'%')",

			"offer.amount >= #{offerList.offer_amountRange.begin}",
			"offer.amount <= #{offerList.offer_amountRange.end}",

			"offer.offerCondition = #{offerList.offer.offerCondition}",

			"offer.status = #{offerList.offer.status}",

			"offer.signBackAmount >= #{offerList.offer_signBackAmountRange.begin}",
			"offer.signBackAmount <= #{offerList.offer_signBackAmountRange.end}",

			"offer.conditionExpiry >= #{offerList.offer_conditionExpiryRange.begin}",
			"offer.conditionExpiry <= #{offerList.offer_conditionExpiryRange.end}",

			"offer.realEstateListing.id = #{offerList.offer.realEstateListing.id}",

			"offer.closingDate >= #{offerList.offer_closingDateRange.begin}",
			"offer.closingDate <= #{offerList.offer_closingDateRange.end}",

			"lower(offer.comments) like concat(lower(#{offerList.offer.comments}),'%')",

			"offer.dateCreated <= #{offerList.dateCreatedRange.end}",
			"offer.dateCreated >= #{offerList.dateCreatedRange.begin}",};

	public Offer getOffer() {
		return offer;
	}

	@Override
	public Class<Offer> getEntityClass() {
		return Offer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedOffer")
	public void onArchive() {
		refresh();
	}
}
