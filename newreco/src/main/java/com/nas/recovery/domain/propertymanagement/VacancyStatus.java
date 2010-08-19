package com.nas.recovery.domain.propertymanagement;

public enum VacancyStatus {

	Secured,

	PreservationOrder,

	AnticipatedJudgement,

	Unsecured,

	;

	VacancyStatus() {
	}

	public String getName() {
		return this.toString();
	}
}
