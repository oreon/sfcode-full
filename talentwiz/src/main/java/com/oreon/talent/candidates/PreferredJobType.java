package com.oreon.talent.candidates;

public enum PreferredJobType {

	CONTRACT,

	PERMANENT,

	EITHER,

	;

	PreferredJobType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
