package com.hrb.tservices.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.dbc.AssertContractCondition;
import org.apache.commons.lang.StringUtils;

/** 
 * 
 */
public abstract class RRSPCalculatorBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * 
	  @param taxYear  Integer   
	 *	  @param province  String   
	 *	  @param userDeductionLimit  Long   
	 *	  @param spouseDeductionLimit  Long   
	 *	  @param userIncome  Long   
	 *	  @param spousIncome  Long   
	 *	  @param userContribution  Long   
	 *	  @param spouseContribution  Long   
	 *	  
	 *	  @param securityKey  String   
	 *	  @param maxResults  Integer Number of office locations to be returned - defaults to 5 if not specified  
	 *
	 * @return RRSPCalculation 
	 */
	@Restrict("#{s:hasPermission('RRSPCalculator','getRRSPCalculation')}")
	public com.hrb.tservices.dtos.RRSPCalculation getRRSPCalculation(
			Integer taxYear, String province, Long userDeductionLimit,
			Long spouseDeductionLimit, Long userIncome, Long spousIncome,
			Long userContribution, Long spouseContribution, String securityKey,
			Integer maxResults) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(taxYear != null,
					"taxYear is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		try {

			AssertContractCondition.enforce(province != null,
					"province is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		try {

			AssertContractCondition.enforce(userIncome != null,
					"userIncome is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		try {

			AssertContractCondition.enforce(securityKey != null,
					"securityKey is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doGetRRSPCalculation(taxYear, province, userDeductionLimit,
				spouseDeductionLimit, userIncome, spousIncome,
				userContribution, spouseContribution, securityKey, maxResults);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.RRSPCalculation doGetRRSPCalculation(
			Integer taxYear, String province, Long userDeductionLimit,
			Long spouseDeductionLimit, Long userIncome, Long spousIncome,
			Long userContribution, Long spouseContribution, String securityKey,
			Integer maxResults);

}
