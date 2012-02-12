package com.hrb.tservices.websvc.taxnews;

import javax.jws.WebService;
import com.hrb.tservices.domain.taxnews.TaxNewsMetrics;
import java.util.List;

@WebService
public interface TaxNewsMetricsWebService {

	public TaxNewsMetrics loadById(Long id);

	public List<TaxNewsMetrics> findByExample(
			TaxNewsMetrics exampleTaxNewsMetrics);

	public void save(TaxNewsMetrics taxNewsMetrics);

}
