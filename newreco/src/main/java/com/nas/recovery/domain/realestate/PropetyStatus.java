package com.nas.recovery.domain.realestate;

public enum PropetyStatus {

	DRIVE_BY,

	RIGHT_TO_SELL,

	LISTED,

	CONDITIONAL_SALE,

	;

	PropetyStatus() {
	}

	public String getName() {
		return this.toString();
	}
}
