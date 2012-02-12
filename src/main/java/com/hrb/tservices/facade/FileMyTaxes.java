package com.hrb.tservices.facade;

import java.util.Date;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.web.ServletContexts;
import org.witchcraft.exceptions.ContractViolationException;

import com.hrb.tservices.domain.department.Partner;
import com.hrb.tservices.domain.message.MarketingMessage;
import com.hrb.tservices.domain.message.MarketingMessageMetrics;
import com.hrb.tservices.domain.message.MessageTranslation;
import com.hrb.tservices.domain.metrics.ClientType;
import com.hrb.tservices.domain.taxnews.Language;
import com.hrb.tservices.dtos.OfferInvite;
import com.hrb.tservices.web.action.department.PartnerAction;
import com.hrb.tservices.web.action.message.MarketingMessageMetricsAction;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Name("fileMyTaxes")
public class FileMyTaxes extends FileMyTaxesBase {

	@In(create = true)
	PartnerAction partnerAction;

	@In(create = true)
	MarketingMessageMetricsAction marketingMessageMetricsAction;

	@In
	protected FullTextEntityManager entityManager;

	@Override
	public Response doViewOffer(String language, String securityKey ) {
		
		Partner partner = getCurrentPartner();
		Language lang = getLanguageEnum(language, partner);
		MessageTranslation trans = getMarketingMessage(partner, lang);
		updateMetricsForClick();
		return createRedirectResponse(trans.getHyperLink());
	}

	

	private void updateMetricsForClick() {
		MarketingMessageMetrics marketingMessageMetrics = (MarketingMessageMetrics) ServletContexts
				.instance().getRequest().getSession().getAttribute(
						AppConstants.MARKETING_MSG_METRICS);
		if (marketingMessageMetrics != null) {
			marketingMessageMetrics.setDateClicked(new Date());
			marketingMessageMetricsAction.persist(marketingMessageMetrics);
		}

	}

	@Override
	// /seam/resource/rest/fileMyTaxesRestService/viewOfferInvite?securityKey=3456&language=french
	public OfferInvite doViewOfferInvite(
			String language, String securityKey) {

		Partner partner = getCurrentPartner();

		Language lang = getLanguageEnum(language, partner);

		MessageTranslation trans = getMarketingMessage(partner, lang);
		updateMetrics(partner, lang, trans);
		OfferInvite marketingMessageDTO = new OfferInvite();

		try {
			BeanUtils.copyProperties(marketingMessageDTO, trans);
			marketingMessageDTO.setHyperLink("/seam/resource/rest/fileMyTaxesRestService/viewOffer");
			//marketingMessageDTO.setHyperLink(appendTargetMethod(marketingMessageDTO.getHyperLink()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marketingMessageDTO;
	}

	private String appendTargetMethod(String hyperLink) {
		hyperLink += hyperLink;
		return null;
	}



	protected void updateMetrics(Partner partner, Language language,
			MessageTranslation trans) {
		MarketingMessageMetrics marketingMessageMetrics = new MarketingMessageMetrics();
		marketingMessageMetrics.setPartner(partner);
		marketingMessageMetrics.setClientType(entityManager.find(
				ClientType.class, 1L));
		marketingMessageMetrics.setMessageTranslation(trans);
		marketingMessageMetrics.setLanguage(language);
		marketingMessageMetricsAction.persist(marketingMessageMetrics);
		ServletContexts.instance().getRequest().getSession().setAttribute(
				AppConstants.MARKETING_MSG_METRICS, marketingMessageMetrics);
	}

	// @Override
	// 
	@Transactional
	public MessageTranslation getMarketingMessage(Partner partner,
			Language language) {

		if (partner == null)
			throw new ContractViolationException("Invalid partner id ");

		MarketingMessage message = partner.getMarketingMessage();
		Set<MessageTranslation> translations = message.getMessageTranslations();

		for (MessageTranslation messageTranslation : translations) {
			if (messageTranslation.getLanguage().equals(
					language)) {
				// messageTranslation.setMarketingMessage(null);
				return messageTranslation;
			}
		}

		return null;
	}

}
