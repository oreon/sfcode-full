
	
package com.hrb.tservices.web.action.faq;
	

import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.QuestionTranslation;
import com.hrb.tservices.domain.taxnews.Language;
import com.hrb.tservices.domain.taxnews.TaxNewsTranslation;

	
//@Scope(ScopeType.CONVERSATION)
@Name("faqQuestionAction")
public class FaqQuestionAction extends FaqQuestionActionBase implements java.io.Serializable{
	
	@Override
	public void prePopulateListQuestionTranslations() {
		if (listQuestionTranslations.isEmpty()) {
			QuestionTranslation transfr = new QuestionTranslation();
			transfr.setLanguage(Language.FRENCH);
			transfr.setFaqQuestion(instance);

			QuestionTranslation transen = new QuestionTranslation();
			transen.setLanguage(Language.ENGLISH);
			transen.setFaqQuestion(instance);

			listQuestionTranslations.add(transen);
			listQuestionTranslations.add(transfr);
		}
		
	}
	
	
}
	