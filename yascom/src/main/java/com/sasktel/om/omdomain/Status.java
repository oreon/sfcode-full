package com.sasktel.om.omdomain;

public enum Status {

	ACCEPTED,

	STARTED,

	COMPLETED,

	;

	Status() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
