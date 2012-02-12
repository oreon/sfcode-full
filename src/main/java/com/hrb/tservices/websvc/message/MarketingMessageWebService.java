package com.hrb.tservices.websvc.message;

import javax.jws.WebService;
import com.hrb.tservices.domain.message.MarketingMessage;
import java.util.List;

@WebService
public interface MarketingMessageWebService {

	public MarketingMessage loadById(Long id);

	public List<MarketingMessage> findByExample(
			MarketingMessage exampleMarketingMessage);

	public void save(MarketingMessage marketingMessage);

}
