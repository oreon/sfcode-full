package com.oreon.talent.candidates;

public enum Availibility {

	IMMEDIATE,

	TWO_WEEKS,

	FOUR_WEEKS,

	;

	Availibility() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
