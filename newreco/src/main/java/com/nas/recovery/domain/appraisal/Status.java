package com.nas.recovery.domain.appraisal;

public enum Status {

	ORDERED,

	ACCEPTED,

	APPOINTMENT,

	COMPLETED,

	;

	Status() {
	}

	public String getName() {
		return this.toString();
	}
}
