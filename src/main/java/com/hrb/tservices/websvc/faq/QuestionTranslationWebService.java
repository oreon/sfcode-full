package com.hrb.tservices.websvc.faq;

import javax.jws.WebService;
import com.hrb.tservices.domain.faq.QuestionTranslation;
import java.util.List;

@WebService
public interface QuestionTranslationWebService {

	public QuestionTranslation loadById(Long id);

	public List<QuestionTranslation> findByExample(
			QuestionTranslation exampleQuestionTranslation);

	public void save(QuestionTranslation questionTranslation);

}
