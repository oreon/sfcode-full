package com.hrb.tservices.websvc.taxnews;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.taxnews.TaxNewsMetrics;

@WebService(endpointInterface = "com.hrb.tservices.websvc.taxnews.TaxNewsMetricsWebService", serviceName = "TaxNewsMetricsWebService")
@Name("taxNewsMetricsWebService")
public class TaxNewsMetricsWebServiceImpl implements TaxNewsMetricsWebService {

	@In(create = true)
	com.hrb.tservices.web.action.taxnews.TaxNewsMetricsAction taxNewsMetricsAction;

	public TaxNewsMetrics loadById(Long id) {
		return taxNewsMetricsAction.loadFromId(id);
	}

	public List<TaxNewsMetrics> findByExample(
			TaxNewsMetrics exampleTaxNewsMetrics) {
		return taxNewsMetricsAction.search(exampleTaxNewsMetrics);
	}

	public void save(TaxNewsMetrics taxNewsMetrics) {
		taxNewsMetricsAction.persist(taxNewsMetrics);
	}

}
