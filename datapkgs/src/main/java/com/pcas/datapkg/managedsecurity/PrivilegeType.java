package com.pcas.datapkg.managedsecurity;

public enum PrivilegeType {

	READ,

	WRITE,

	DELETE,

	CREATE,

	;

	PrivilegeType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
