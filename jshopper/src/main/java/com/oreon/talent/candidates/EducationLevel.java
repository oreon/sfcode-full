package com.oreon.talent.candidates;

public enum EducationLevel {

	HIGH_SCHOOL,

	DIPLOMA,

	UNDER_GRAD,

	GRAD,

	PHD,

	;

	EducationLevel() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
