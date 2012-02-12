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
 * Responsible for authenticating the user and saving the session token
 */
public abstract class AuthenticationBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * Will return the session token if login is successful, null if it fails
	  @param username  String   
	 *	  @param password  String   
	 *	  
	 *
	 * @return AuthenticationResponse 
	 */
	@Restrict("#{s:hasPermission('Authentication','login')}")
	public com.hrb.tservices.dtos.AuthenticationResponse login(String username,
			String password) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(username != null,
					"username is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		try {

			AssertContractCondition.enforce(password != null,
					"password is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doLogin(username, password);
	}

	/** 
	 * [Will return the session token if login is successful, null if it fails]
	 */

	protected abstract com.hrb.tservices.dtos.AuthenticationResponse doLogin(
			String username, String password);

	/** 
	 * this method will invalidate the session and logout the current user.
	  
	 *
	 * @return AuthenticationResponse 
	 */
	@Restrict("#{s:hasPermission('Authentication','logout')}")
	public com.hrb.tservices.dtos.AuthenticationResponse logout() {

		StringBuilder exceptionMessages = new StringBuilder();

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doLogout();
	}

	/** 
	 * [this method will invalidate the session and logout the current user.]
	 */

	protected abstract com.hrb.tservices.dtos.AuthenticationResponse doLogout();

}
