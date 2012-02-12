package com.hrb.tservices.websvc.message;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.message.MarketingMessageMetrics;

@WebService(endpointInterface = "com.hrb.tservices.websvc.message.MarketingMessageMetricsWebService", serviceName = "MarketingMessageMetricsWebService")
@Name("marketingMessageMetricsWebService")
public class MarketingMessageMetricsWebServiceImpl
		implements
			MarketingMessageMetricsWebService {

	@In(create = true)
	com.hrb.tservices.web.action.message.MarketingMessageMetricsAction marketingMessageMetricsAction;

	public MarketingMessageMetrics loadById(Long id) {
		return marketingMessageMetricsAction.loadFromId(id);
	}

	public List<MarketingMessageMetrics> findByExample(
			MarketingMessageMetrics exampleMarketingMessageMetrics) {
		return marketingMessageMetricsAction
				.search(exampleMarketingMessageMetrics);
	}

	public void save(MarketingMessageMetrics marketingMessageMetrics) {
		marketingMessageMetricsAction.persist(marketingMessageMetrics);
	}

}
