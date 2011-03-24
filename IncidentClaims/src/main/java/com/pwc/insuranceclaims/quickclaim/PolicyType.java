package com.pwc.insuranceclaims.quickclaim;

public enum PolicyType {

	HEALTH,

	DENTAL,

	UNIVERSAL,

	;

	PolicyType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
