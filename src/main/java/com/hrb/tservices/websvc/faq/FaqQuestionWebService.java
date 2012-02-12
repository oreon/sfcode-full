package com.hrb.tservices.websvc.faq;

import javax.jws.WebService;
import com.hrb.tservices.domain.faq.FaqQuestion;
import java.util.List;

@WebService
public interface FaqQuestionWebService {

	public FaqQuestion loadById(Long id);

	public List<FaqQuestion> findByExample(FaqQuestion exampleFaqQuestion);

	public void save(FaqQuestion faqQuestion);

}
