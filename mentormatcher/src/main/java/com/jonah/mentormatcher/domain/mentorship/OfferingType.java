package com.jonah.mentormatcher.domain.mentorship;

public enum OfferingType {

	ORGANIZATION,

	DEPARTMENT,

	CITY,

	;

	OfferingType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
