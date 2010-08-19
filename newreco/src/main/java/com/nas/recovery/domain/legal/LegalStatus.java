package com.nas.recovery.domain.legal;

public enum LegalStatus {

	DEMAND_ISSUED,

	SOC_ISSUED,

	SOC_SERVED,

	NOS_ISSUED,

	NOS_EXPIRED_VT,

	NOS_EXPIRED_ER,

	ON_HOLD_PENDING_SALE,

	SOLD_PENDING_CD,

	;

	LegalStatus() {
	}

	public String getName() {
		return this.toString();
	}
}
