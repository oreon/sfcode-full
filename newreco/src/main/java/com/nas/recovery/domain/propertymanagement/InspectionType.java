package com.nas.recovery.domain.propertymanagement;

public enum InspectionType {

	Monitoring,

	Securing,

	;

	InspectionType() {
	}

	public String getName() {
		return this.toString();
	}
}
