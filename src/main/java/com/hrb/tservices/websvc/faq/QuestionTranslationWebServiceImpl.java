package com.hrb.tservices.websvc.faq;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.QuestionTranslation;

@WebService(endpointInterface = "com.hrb.tservices.websvc.faq.QuestionTranslationWebService", serviceName = "QuestionTranslationWebService")
@Name("questionTranslationWebService")
public class QuestionTranslationWebServiceImpl
		implements
			QuestionTranslationWebService {

	@In(create = true)
	com.hrb.tservices.web.action.faq.QuestionTranslationAction questionTranslationAction;

	public QuestionTranslation loadById(Long id) {
		return questionTranslationAction.loadFromId(id);
	}

	public List<QuestionTranslation> findByExample(
			QuestionTranslation exampleQuestionTranslation) {
		return questionTranslationAction.search(exampleQuestionTranslation);
	}

	public void save(QuestionTranslation questionTranslation) {
		questionTranslationAction.persist(questionTranslation);
	}

}
