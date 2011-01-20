package org.wc.trackrite.issues;

public enum Severity {

	ONE,

	TWO,

	THREE,

	FOUR,

	;

	Severity() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
