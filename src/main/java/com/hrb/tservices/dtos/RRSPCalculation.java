package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class RRSPCalculation extends com.hrb.tservices.dtos.BaseDTO {

	private Integer userContribution;

	private Integer spouseContribution;

	private Integer totalTaxSaving;

	public void setUserContribution(Integer userContribution) {
		this.userContribution = userContribution;
	}

	public Integer getUserContribution() {
		return userContribution;
	}

	public void setSpouseContribution(Integer spouseContribution) {
		this.spouseContribution = spouseContribution;
	}

	public Integer getSpouseContribution() {
		return spouseContribution;
	}

	public void setTotalTaxSaving(Integer totalTaxSaving) {
		this.totalTaxSaving = totalTaxSaving;
	}

	public Integer getTotalTaxSaving() {
		return totalTaxSaving;
	}

}
