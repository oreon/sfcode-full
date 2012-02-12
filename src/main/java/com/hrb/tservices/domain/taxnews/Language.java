package com.hrb.tservices.domain.taxnews;

public enum Language {

	ENGLISH,

	FRENCH,

	;

	Language() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
