package com.oreon.callosum.patient;

public enum Route {

	PIO,

	IM,

	IV,

	TOPICAL,

	;

	Route() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
