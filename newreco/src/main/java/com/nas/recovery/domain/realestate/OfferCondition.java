package com.nas.recovery.domain.realestate;

public enum OfferCondition {

	Inspection,

	Finance,

	Appraisal,

	SCC,

	Insurance,

	Other,

	;

	OfferCondition() {
	}

	public String getName() {
		return this.toString();
	}
}
