package com.oreon.smartsis.domain;

public enum DiscontinueReason {

	DISCIPLINARY,

	ACADEMIC,

	FINANCIAL_OVERDUE_FEES,

	DROPPED_OUT,

	PASSED_OUT,

	;

	DiscontinueReason() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
