package com.oreon.phonestore.domain.commerce;

public enum CustomerType {

	STANDARD,

	GOLD,

	PLATINUM,

	;

	CustomerType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
