package com.hrb.tservices.websvc.taxnews;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.taxnews.TaxNews;

@WebService(endpointInterface = "com.hrb.tservices.websvc.taxnews.TaxNewsWebService", serviceName = "TaxNewsWebService")
@Name("taxNewsWebService")
public class TaxNewsWebServiceImpl implements TaxNewsWebService {

	@In(create = true)
	com.hrb.tservices.web.action.taxnews.TaxNewsAction taxNewsAction;

	public TaxNews loadById(Long id) {
		return taxNewsAction.loadFromId(id);
	}

	public List<TaxNews> findByExample(TaxNews exampleTaxNews) {
		return taxNewsAction.search(exampleTaxNews);
	}

	public void save(TaxNews taxNews) {
		taxNewsAction.persist(taxNews);
	}

}
