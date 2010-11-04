package org.wc.trackrite.issues;

public enum Priority {

	SHOW_STOPPER,

	CRITICAL_URGENT,

	CRITICAL_NOT_URGENT,

	NON_CRITICAL,

	LANGUAGE,

	COSMETIC,

	;

	Priority() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
