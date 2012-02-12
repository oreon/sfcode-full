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
public abstract class FileMyTaxesBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * 
	  
	 *	  @param language  String   
	 *	  @param securityKey  String   
	 *
	 * @return OfferInvite 
	 */
	@Restrict("#{s:hasPermission('FileMyTaxes','viewOfferInvite')}")
	public com.hrb.tservices.dtos.OfferInvite viewOfferInvite(String language,
			String securityKey) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(securityKey != null,
					"securityKey is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doViewOfferInvite(language, securityKey);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.OfferInvite doViewOfferInvite(
			String language, String securityKey);

	/** 
	 * 
	  
	 *	  @param language  String   
	 *	  @param securityKey  String   
	 *
	 * @return Response 
	 */
	@Restrict("#{s:hasPermission('FileMyTaxes','viewOffer')}")
	public Response viewOffer(String language, String securityKey) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(securityKey != null,
					"securityKey is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doViewOffer(language, securityKey);
	}

	/** 
	 * []
	 */

	protected abstract Response doViewOffer(String language, String securityKey);

}
