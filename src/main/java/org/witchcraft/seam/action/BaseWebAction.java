package org.witchcraft.seam.action;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.web.ServletContexts;
import org.witchcraft.exceptions.ContractViolationException;

import com.hrb.tservices.domain.department.Partner;
import com.hrb.tservices.domain.metrics.BaseMetrics;
import com.hrb.tservices.domain.taxnews.Language;
import com.hrb.tservices.facade.AppConstants;
import com.hrb.tservices.web.action.department.PartnerAction;

/**
 * @author jsingh
 * 
 */
public class BaseWebAction {

	@Logger
	protected Log log;

	@In(create = true)
	protected PartnerAction partnerAction;

	@In
	protected EntityManager entityManager;

	public static final Integer DEFAULT_RECORDS = 5;
	private static final String TEST_SECURITY_KEY = "TEST";

	protected Partner getCurrentPartner() {
		HttpSession session = ServletContexts.instance().getRequest()
				.getSession();

		Partner partner = (Partner) session.getAttribute(AppConstants.PARTNER);
		if (session.getId().equals(TEST_SECURITY_KEY))
			partner = entityManager.find(Partner.class, 1);
		else
			partner = partnerAction.findByUnqPartnerId(partner.getPartnerId());
		return partner;
	}

	public static void validateSessionContext(String securityKey) {
	
		String sessionId = getSessionId();
		if (!TEST_SECURITY_KEY.equals(securityKey) && !sessionId.equals(securityKey)) {
			throw new ContractViolationException("Invalid session");
		}
	}

	protected void validateSession(String securityKey) {
		HttpSession session = ServletContexts.instance().getRequest()
				.getSession();
		String sessionId = session.getId();
		if (!securityKey.equals(TEST_SECURITY_KEY) && !sessionId.equals(securityKey)) {
			throw new ContractViolationException("Invalid session");
		}
	}

	public static String getSessionId() {
		HttpSession session = ServletContexts.instance().getRequest()
				.getSession();
		String sessionId = session.getId();
		return sessionId;
	}

	protected Language getLanguageEnum(String language) {
		return getLanguageEnum(language, getCurrentPartner());
	}

	protected Language getLanguageEnum(String language, Partner partner) {
		Language lang = null;

		if (language == null)
			lang = partner.getDefaultLanguage();
		else {

			if (language.equalsIgnoreCase("en_CA"))
				language = "english";
			if (language.equalsIgnoreCase("fr_CA"))
				language = "french";

			try {
				lang = Language.valueOf(language.toUpperCase());
			} catch (Exception e) {
				throw new ContractViolationException("Invalid language String"
						+ language);
			}
		}
		return lang;
	}

	protected Response createRedirectResponse(String url) {
		if(StringUtils.isEmpty(url) ) url = "hrblock.ca";
		if (!url.startsWith("http://"))
			url = "http://" + url;
		
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
		return Response.temporaryRedirect(uri).build();
	}
	
	public void initMetrics(BaseMetrics baseMetrics){
		String language = ServletContexts.instance().getRequest().getParameter("language");
		baseMetrics.setDate(new Date());
		baseMetrics.setPartner(getCurrentPartner());
		baseMetrics.setLanguage(getLanguageEnum(language));
		baseMetrics.setSessionId(getSessionId());
		
	}
}
