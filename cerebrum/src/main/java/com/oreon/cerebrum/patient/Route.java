package com.oreon.cerebrum.patient;

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
