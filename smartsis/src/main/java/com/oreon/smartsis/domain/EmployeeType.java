package com.oreon.smartsis.domain;

public enum EmployeeType {

	Teacher,

	Manager,

	Clerk,

	;

	EmployeeType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
