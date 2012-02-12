package com.hrb.tservices.websvc.faq;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.FaqQuestionMetrics;

@WebService(endpointInterface = "com.hrb.tservices.websvc.faq.FaqQuestionMetricsWebService", serviceName = "FaqQuestionMetricsWebService")
@Name("faqQuestionMetricsWebService")
public class FaqQuestionMetricsWebServiceImpl
		implements
			FaqQuestionMetricsWebService {

	@In(create = true)
	com.hrb.tservices.web.action.faq.FaqQuestionMetricsAction faqQuestionMetricsAction;

	public FaqQuestionMetrics loadById(Long id) {
		return faqQuestionMetricsAction.loadFromId(id);
	}

	public List<FaqQuestionMetrics> findByExample(
			FaqQuestionMetrics exampleFaqQuestionMetrics) {
		return faqQuestionMetricsAction.search(exampleFaqQuestionMetrics);
	}

	public void save(FaqQuestionMetrics faqQuestionMetrics) {
		faqQuestionMetricsAction.persist(faqQuestionMetrics);
	}

}
