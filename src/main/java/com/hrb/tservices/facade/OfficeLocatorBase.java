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
public abstract class OfficeLocatorBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * 
	  @param postalCode  String   
	 *	  @param language  String   
	 *	  
	 *	  @param securityKey  String   
	 *	  @param maxResults  Integer Number of office locations to be returned - defaults to 5 if not specified  
	 *
	 * @return OfficeLocationList 
	 */
	@Restrict("#{s:hasPermission('OfficeLocator','locateOfficeByPostalCode')}")
	public com.hrb.tservices.dtos.OfficeLocationList locateOfficeByPostalCode(
			String postalCode, String language, String securityKey,
			Integer maxResults) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(postalCode != null,
					"postalCode is required");

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

		return doLocateOfficeByPostalCode(postalCode, language, securityKey,
				maxResults);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.OfficeLocationList doLocateOfficeByPostalCode(
			String postalCode, String language, String securityKey,
			Integer maxResults);

	/** 
	 * 
	  @param address  String   
	 *	  @param language  String   
	 *	  
	 *	  @param city  String   
	 *	  @param province  String   
	 *	  @param securityKey  String   
	 *	  @param maxResults  Integer Number of office locations to be returned - defaults to 5 if not specified  
	 *
	 * @return OfficeLocationList 
	 */
	@Restrict("#{s:hasPermission('OfficeLocator','locateOfficeByAddress')}")
	public com.hrb.tservices.dtos.OfficeLocationList locateOfficeByAddress(
			String address, String language, String city, String province,
			String securityKey, Integer maxResults) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(address != null,
					"address is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		try {

			AssertContractCondition.enforce(city != null, "city is required");

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

			AssertContractCondition.enforce(securityKey != null,
					"securityKey is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doLocateOfficeByAddress(address, language, city, province,
				securityKey, maxResults);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.OfficeLocationList doLocateOfficeByAddress(
			String address, String language, String city, String province,
			String securityKey, Integer maxResults);

}
