package com.oreon.smartsis.attendance;

public enum AbsenceCode {

	ABSENT,

	LEAVE,

	SICK_LEAVE,

	;

	AbsenceCode() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
