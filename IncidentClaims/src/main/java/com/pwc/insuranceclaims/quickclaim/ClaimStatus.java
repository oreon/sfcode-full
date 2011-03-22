package com.pwc.insuranceclaims.quickclaim;

public enum ClaimStatus {

	AUTO_APPROVED,

	IN_PROCESS,

	MANUAL_APPROVED,

	REJECTEd,

	;

	ClaimStatus() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
