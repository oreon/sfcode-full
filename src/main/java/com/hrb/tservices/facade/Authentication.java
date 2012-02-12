package com.hrb.tservices.facade;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.web.ServletContexts;
import org.witchcraft.dbc.AssertContractCondition;
import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.seam.security.Authenticator;

import com.hrb.tservices.domain.department.AuthenticationMetrics;
import com.hrb.tservices.domain.department.Partner;
import com.hrb.tservices.dtos.AuthenticationResponse;
import com.hrb.tservices.dtos.Status;
import com.hrb.tservices.web.action.department.AuthenticationMetricsAction;
import com.hrb.tservices.web.action.department.PartnerAction;

@Name("authentication")
public class Authentication extends AuthenticationBase {
	
	@In(create = true)
	Credentials credentials;

	@In(create = true)
	Authenticator authenticator;

	@In(create = true)
	PartnerAction partnerAction;

	@In(create = true)
	Identity identity;
	
	@In(create = true)
	AuthenticationMetricsAction authenticationMetricsAction;

	@Override
	public AuthenticationResponse login(String username, String password) {
		// TODO Auto-generated method stub

		StringBuilder exceptionMessages = new StringBuilder();

		try {
			AssertContractCondition.enforce(username != null,
					"username is required");

			AssertContractCondition.enforce(password != null,
					"password is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		// return doLogin(username, password);
		return doLogin(username, password);
	}

	@Override
	public AuthenticationResponse doLogin(String username, String password) {

		credentials.setUsername(username);
		credentials.setPassword(password);

		AuthenticationResponse response = new AuthenticationResponse();

		if (authenticator.authenticate()) {
			identity.login();
			
	
			HttpSession session = ServletContexts.instance().getRequest()
					.getSession();
			Partner partner = partnerAction.getCurrentLoggedInPartner();
			session.setAttribute(AppConstants.PARTNER, partner);
			String sessionId = session.getId();
			response.setResponseMessage(sessionId);
			updateMetrics(true);
		} else {
			
			response.setResponseStatus(Status.INVALID_INPUT);
			response.setResponseMessage("Invalid username/password");
		}

		return response;
	}

	@Override
	public AuthenticationResponse doLogout() {
		identity.logout();
		org.jboss.seam.web.Session.instance().invalidate();

		return new AuthenticationResponse();
	}
	
	private void updateMetrics(boolean  result){
		AuthenticationMetrics metrics = new AuthenticationMetrics();
		initMetrics(metrics);
		
		metrics.setSucceeded(result);
		authenticationMetricsAction.persist(metrics);
	}

}
