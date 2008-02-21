package com.oreon.kgauge.reports;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

public class CandidateRegisterationByCountryStateReport
		extends
			CandidateRegisterationByCountryStateReportBase {

	private static final Logger log = Logger
			.getLogger(CandidateRegisterationByCountryStateReport.class);

	public CandidateRegisterationByCountryStateReport candidateRegisterationByCountryStateReportInstance() {
		return this;
	}
}
