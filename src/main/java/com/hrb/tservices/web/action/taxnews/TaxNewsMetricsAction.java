package com.hrb.tservices.web.action.taxnews;

import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.FaqQuestionMetrics;
import com.hrb.tservices.domain.metrics.ClientType;
import com.hrb.tservices.domain.taxnews.TaxNews;
import com.hrb.tservices.domain.taxnews.TaxNewsMetrics;

//@Scope(ScopeType.CONVERSATION)
@Name("taxNewsMetricsAction")
public class TaxNewsMetricsAction extends TaxNewsMetricsActionBase implements
		java.io.Serializable {

	@Override
	protected TaxNewsMetrics createInstance() {
		// TODO Auto-generated method stub
		TaxNewsMetrics metrics = super.createInstance();
		metrics.setClientType(getEntityManager().find(ClientType.class, 1L));
		return metrics;
	}

	public TaxNewsMetrics getMetricsByArticleAndSession(TaxNews news,
			String sessionId) {
		String qry = "Select f from TaxNewsMetrics f where f.taxNews = ?1 and f.sessionId = ?2 ";
		TaxNewsMetrics metrics = executeSingleResultQuery(qry, news, sessionId);
		return metrics;
	}

}
