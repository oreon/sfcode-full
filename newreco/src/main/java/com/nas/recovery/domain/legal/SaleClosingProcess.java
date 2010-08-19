package com.nas.recovery.domain.legal;

public enum SaleClosingProcess {

	AGREEMENT_PANDS,

	CLOSING_DOCUMENTS,

	SALE_COMPLETED,

	SALE_PROCEEDS_DEPOSITED,

	CLOSING_REPORT,

	;

	SaleClosingProcess() {
	}

	public String getName() {
		return this.toString();
	}
}
