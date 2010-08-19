package com.nas.recovery.domain.legal;

public enum SpecialHandling {

	BREACH_OF_CONTRACT,

	DRUG_LAB_OR_GROWUP,

	ENVIRONMENTAL,

	FRAUD,

	IMPARIMENT_INSURANCE_CLAIM,

	LITIGATION,

	TITLE_INSURANCE,

	OTHER,

	;

	SpecialHandling() {
	}

	public String getName() {
		return this.toString();
	}
}
