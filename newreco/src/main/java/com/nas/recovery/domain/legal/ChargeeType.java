package com.nas.recovery.domain.legal;

public enum ChargeeType {

	Mortgage,

	Mortgage_SCL,

	Lien_Condo,

	Lien_Construction,

	Mechanics,

	Other,

	;

	ChargeeType() {
	}

	public String getName() {
		return this.toString();
	}
}
