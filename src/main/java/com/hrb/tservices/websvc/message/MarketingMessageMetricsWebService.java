package com.hrb.tservices.websvc.message;

import javax.jws.WebService;
import com.hrb.tservices.domain.message.MarketingMessageMetrics;
import java.util.List;

@WebService
public interface MarketingMessageMetricsWebService {

	public MarketingMessageMetrics loadById(Long id);

	public List<MarketingMessageMetrics> findByExample(
			MarketingMessageMetrics exampleMarketingMessageMetrics);

	public void save(MarketingMessageMetrics marketingMessageMetrics);

}
