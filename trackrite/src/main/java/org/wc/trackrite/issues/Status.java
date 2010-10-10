package org.wc.trackrite.issues;

public enum Status {

	Unassigned,

	Started,

	ReadyForQA,

	Closed,

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
