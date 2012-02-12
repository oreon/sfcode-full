
	
package com.hrb.tservices.web.action.faq;
	

import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.FaqQuestion;
import com.hrb.tservices.domain.faq.FaqQuestionMetrics;

	
//@Scope(ScopeType.CONVERSATION)
@Name("faqQuestionMetricsAction")
public class FaqQuestionMetricsAction extends FaqQuestionMetricsActionBase implements java.io.Serializable{
	
	public FaqQuestionMetrics getMetricsByQuestionAndSession(FaqQuestion question ,String sessionId){
		String qry = "Select f from FaqQuestionMetrics f where f.faqQuestion = ?1 and f.sessionId = ?2 ";
		try{
			executeSingleResultQuery(qry, question, sessionId );
		}catch(Exception e){
			e.printStackTrace();
		}
		return executeSingleResultQuery(qry, question, sessionId );
	}
}
	