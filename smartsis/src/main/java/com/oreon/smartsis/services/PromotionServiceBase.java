package com.oreon.smartsis.services;

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
 * This class is resposnible for prmoting the students to next grade provided they have cleared the exam.
 */
public abstract class PromotionServiceBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * 
	  
	 *
	 * @return Boolean 
	 */
	@Restrict("#{s:hasPermission('PromotionService','promote')}")
	public Boolean promote() {

		StringBuilder exceptionMessages = new StringBuilder();

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doPromote();
	}

	/** 
	 * []
	 */

	protected abstract Boolean doPromote();

}
