package com.jonah.mentormatcher.domain;

public enum Gender {

	F,

	M,

	;

	Gender() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
