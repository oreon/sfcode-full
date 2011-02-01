package com.oreon.tapovan.domain;

public enum Frequency {

	MONTHLY,

	QUARTERLY,

	SEMI_ANNUALLY,

	ANNUALLY,

	;

	Frequency() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
