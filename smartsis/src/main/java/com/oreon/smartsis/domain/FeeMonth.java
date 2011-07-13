package com.oreon.smartsis.domain;

public enum FeeMonth {

	JAN,

	FEB,

	MAR,

	APR,

	MAY,

	JUN,

	JUL,

	AUG,

	SEP,

	OCT,

	NOV,

	DEC,

	;

	FeeMonth() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
