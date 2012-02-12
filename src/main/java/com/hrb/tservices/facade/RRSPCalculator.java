package com.hrb.tservices.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.jboss.seam.annotations.Name;

import com.hrb.tservices.dtos.RRSPCalculation;
import com.sun.faces.lifecycle.UpdateModelValuesPhase;

@Name("rRSPCalculator")
public class RRSPCalculator extends RRSPCalculatorBase {



	@Override
	protected RRSPCalculation doGetRRSPCalculation(Integer taxYear,
			String province, Long userDeductionLimit,
			Long spouseDeductionLimit, Long userIncome, Long spousIncome,
			Long userContribution, Long spouseContribution, String securityKey,
			Integer maxResults) {

		RRSPCalculation calculation = new RRSPCalculation();

		// TODO
		calculation.setSpouseContribution(0);
		calculation.setTotalTaxSaving(34000);
		calculation.setUserContribution(42000);

		updateMetrics();
		return calculation;
	}

	private void updateMetrics() {
		// TODO Auto-generated method stub
		
	}

}
