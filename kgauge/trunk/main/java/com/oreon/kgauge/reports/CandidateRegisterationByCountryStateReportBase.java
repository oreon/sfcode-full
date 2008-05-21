
/**
 * This is generated code - to edit code or override methods use - CandidateRegisterationByCountryStateReport class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.reports;

import java.util.Date;
import org.apache.log4j.Logger;

/**
 * 
 */
public abstract class CandidateRegisterationByCountryStateReportBase
		implements
			java.io.Serializable {
	private static final Logger log = Logger
			.getLogger(CandidateRegisterationByCountryStateReportBase.class);

	private static final long serialVersionUID = 1L;

	protected String firstName;

	/* Default Constructor */
	public CandidateRegisterationByCountryStateReportBase() {
	}

	/* Constructor with all attributes */
	public CandidateRegisterationByCountryStateReportBase(String firstName) {
		this.firstName = firstName;
	}

	/*
	
	 */
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
