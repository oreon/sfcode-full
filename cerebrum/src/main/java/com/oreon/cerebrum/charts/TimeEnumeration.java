package com.oreon.cerebrum.charts;

public enum TimeEnumeration {

	HOUR (60L),

	DAY (1440L),

	WEEK (10080L),

	MONTH (43200L),

	YEAR  (525600L),   

	;

	TimeEnumeration() {
	}
	
	TimeEnumeration(Long time) {
		
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
