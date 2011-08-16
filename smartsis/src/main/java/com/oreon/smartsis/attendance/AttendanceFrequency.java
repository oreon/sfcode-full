package com.oreon.smartsis.attendance;

public enum AttendanceFrequency {

	DAILY,

	TWICE_DAILY,

	BY_SUBJECT,

	;

	AttendanceFrequency() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
