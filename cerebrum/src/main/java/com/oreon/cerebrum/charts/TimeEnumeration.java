package com.oreon.cerebrum.charts;

public enum TimeEnumeration {

	HOUR,

	DAY,

	WEEK,

	MONTH,

	YEAR,

	;

	TimeEnumeration() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
