package com.oreon.talent.candidates;

public enum ChiefExpertise {

	JAVA,

	DOT_NET,

	PHP,

	PROJECT_MANAGEMENT,

	BUSINESS_ANALYST,

	;

	ChiefExpertise() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
