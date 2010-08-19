package com.nas.recovery.domain.appraisal;

public enum ServiceType {

	FULL_SERVICE,

	DRIVE_BY,

	DESKTOP,

	;

	ServiceType() {
	}

	public String getName() {
		return this.toString();
	}
}
