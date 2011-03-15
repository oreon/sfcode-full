package com.pwc.insuranceclaims.quickclaim;

public enum PolicyType {

	HEALTH,

	DENTAL,

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
