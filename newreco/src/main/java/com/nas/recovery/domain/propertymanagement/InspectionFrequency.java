package com.nas.recovery.domain.propertymanagement;

public enum InspectionFrequency {

	SEMI_WEEKLY,

	WEEKLY,

	BIWEEKLY,

	MONTHLY,

	;

	InspectionFrequency() {
	}

	public String getName() {
		return this.toString();
	}
}
