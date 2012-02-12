package com.hrb.tservices.websvc.faq;

import javax.jws.WebService;
import com.hrb.tservices.domain.faq.FaqQuestionMetrics;
import java.util.List;

@WebService
public interface FaqQuestionMetricsWebService {

	public FaqQuestionMetrics loadById(Long id);

	public List<FaqQuestionMetrics> findByExample(
			FaqQuestionMetrics exampleFaqQuestionMetrics);

	public void save(FaqQuestionMetrics faqQuestionMetrics);

}
