package com.nas.recovery.domain.realestate;

public enum OfferStatus {

	Offer,

	SignBackBuyer,

	SignBackSeller,

	SoldFirm,

	ConditionalSale,

	FellThrough,

	Rejected,

	Accepted,

	;

	OfferStatus() {
	}

	public String getName() {
		return this.toString();
	}
}
