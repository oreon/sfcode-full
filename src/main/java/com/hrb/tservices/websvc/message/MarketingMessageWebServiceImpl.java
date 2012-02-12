package com.hrb.tservices.websvc.message;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.message.MarketingMessage;

@WebService(endpointInterface = "com.hrb.tservices.websvc.message.MarketingMessageWebService", serviceName = "MarketingMessageWebService")
@Name("marketingMessageWebService")
public class MarketingMessageWebServiceImpl
		implements
			MarketingMessageWebService {

	@In(create = true)
	com.hrb.tservices.web.action.message.MarketingMessageAction marketingMessageAction;

	public MarketingMessage loadById(Long id) {
		return marketingMessageAction.loadFromId(id);
	}

	public List<MarketingMessage> findByExample(
			MarketingMessage exampleMarketingMessage) {
		return marketingMessageAction.search(exampleMarketingMessage);
	}

	public void save(MarketingMessage marketingMessage) {
		marketingMessageAction.persist(marketingMessage);
	}

}
