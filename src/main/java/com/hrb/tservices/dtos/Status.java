package com.hrb.tservices.dtos;

public enum Status {

	SUCCESS,

	WARNING,

	INVALID_INPUT,

	SERVICE_ERROR,

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
