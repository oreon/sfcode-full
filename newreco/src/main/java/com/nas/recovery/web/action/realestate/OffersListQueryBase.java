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

	private Range<Date> offers_saleDateRange = new Range<Date>();
	public Range<Date> getOffers_saleDateRange() {
		return offers_saleDateRange;
	}
	public void setOffers_saleDate(Range<Date> offers_saleDateRange) {
		this.offers_saleDateRange = offers_saleDateRange;
	}

	private Range<Double> offers_signBackAmountRange = new Range<Double>();
	public Range<Double> getOffers_signBackAmountRange() {
		return offers_signBackAmountRange;
	}
	public void setOffers_signBackAmount(
			Range<Double> offers_signBackAmountRange) {
		this.offers_signBackAmountRange = offers_signBackAmountRange;
	}

	private Range<Double> offers_amountRange = new Range<Double>();
	public Range<Double> getOffers_amountRange() {
		return offers_amountRange;
	}
	public void setOffers_amount(Range<Double> offers_amountRange) {
		this.offers_amountRange = offers_amountRange;
	}

	private static final String[] RESTRICTIONS = {
			"offers.id = #{offersList.offers.id}",

			"offers.saleDate >= #{offersList.offers_saleDateRange.begin}",
			"offers.saleDate <= #{offersList.offers_saleDateRange.end}",

			"offers.signBackAmount >= #{offersList.offers_signBackAmountRange.begin}",
			"offers.signBackAmount <= #{offersList.offers_signBackAmountRange.end}",

			"lower(offers.conditionExpiry) like concat(lower(#{offersList.offers.conditionExpiry}),'%')",

			"lower(offers.purchaser) like concat(lower(#{offersList.offers.purchaser}),'%')",

			"lower(offers.comments) like concat(lower(#{offersList.offers.comments}),'%')",

			"offers.amount >= #{offersList.offers_amountRange.begin}",
			"offers.amount <= #{offersList.offers_amountRange.end}",

			"lower(offers.status) like concat(lower(#{offersList.offers.status}),'%')",

			"offers.realEstateListing = #{offersList.offers.realEstateListing}",

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
