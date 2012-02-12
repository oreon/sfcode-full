package com.hrb.tservices.websvc.faq;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.FaqQuestion;

@WebService(endpointInterface = "com.hrb.tservices.websvc.faq.FaqQuestionWebService", serviceName = "FaqQuestionWebService")
@Name("faqQuestionWebService")
public class FaqQuestionWebServiceImpl implements FaqQuestionWebService {

	@In(create = true)
	com.hrb.tservices.web.action.faq.FaqQuestionAction faqQuestionAction;

	public FaqQuestion loadById(Long id) {
		return faqQuestionAction.loadFromId(id);
	}

	public List<FaqQuestion> findByExample(FaqQuestion exampleFaqQuestion) {
		return faqQuestionAction.search(exampleFaqQuestion);
	}

	public void save(FaqQuestion faqQuestion) {
		faqQuestionAction.persist(faqQuestion);
	}

}
