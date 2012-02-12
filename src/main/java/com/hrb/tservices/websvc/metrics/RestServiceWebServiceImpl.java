package com.hrb.tservices.websvc.metrics;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.metrics.RestService;

@WebService(endpointInterface = "com.hrb.tservices.websvc.metrics.RestServiceWebService", serviceName = "RestServiceWebService")
@Name("restServiceWebService")
public class RestServiceWebServiceImpl implements RestServiceWebService {

	@In(create = true)
	com.hrb.tservices.web.action.metrics.RestServiceAction restServiceAction;

	public RestService loadById(Long id) {
		return restServiceAction.loadFromId(id);
	}

	public List<RestService> findByExample(RestService exampleRestService) {
		return restServiceAction.search(exampleRestService);
	}

	public void save(RestService restService) {
		restServiceAction.persist(restService);
	}

}
