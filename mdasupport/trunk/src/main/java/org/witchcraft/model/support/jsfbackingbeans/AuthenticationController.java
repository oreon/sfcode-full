package org.witchcraft.model.support.jsfbackingbeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.context.HttpSessionContextIntegrationFilter;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * This is the JSF backing bean for authenticating users
 * 
 * @author jsingh
 * 
 */
public class AuthenticationController {

	private String username;
	private String password;
	private static final Logger logger = Logger.getLogger(AuthenticationController.class);

	// injected properties
	private AuthenticationManager authenticationManager;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	@SuppressWarnings("unchecked")
	public String authenticate() {
		String outcome = "failure";

		try {
			final String userName = getUsername();
			final String password = getPassword();
			final UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
					userName, password);

			final HttpServletRequest request = getRequest();
			authReq.setDetails(new WebAuthenticationDetails(request));

			final HttpSession session = request.getSession();
			session
					.setAttribute(
							AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY,
							userName);

			logger.info("Authenticating " + userName + " " + password);

			//perform authentication
			final Authentication auth = getAuthenticationManager()
					.authenticate(authReq);

			/*
			 * initialize the security context.
			 */
			final SecurityContext secCtx = SecurityContextHolder.getContext();
			secCtx.setAuthentication(auth);
			session
					.setAttribute(
							HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY,
							secCtx);

			outcome = "loginSuccesful";
			logger.info("Successfully authenticated " + userName);

		} catch (Exception e) {
			outcome = "failure";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}

		return outcome;
	}

	public void logout(ActionEvent e) {

		final HttpServletRequest request = getRequest();
		request.getSession(false).removeAttribute(
				HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY);

		/*
		 * simulate the SecurityContextLogoutHandler
		 */
		SecurityContextHolder.clearContext();

		request.getSession(false).invalidate();
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	@Required
	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
