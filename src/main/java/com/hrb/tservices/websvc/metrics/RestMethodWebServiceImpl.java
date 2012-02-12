package com.hrb.tservices.websvc.metrics;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.metrics.RestMethod;

@WebService(endpointInterface = "com.hrb.tservices.websvc.metrics.RestMethodWebService", serviceName = "RestMethodWebService")
@Name("restMethodWebService")
public class RestMethodWebServiceImpl implements RestMethodWebService {

	@In(create = true)
	com.hrb.tservices.web.action.metrics.RestMethodAction restMethodAction;

	public RestMethod loadById(Long id) {
		return restMethodAction.loadFromId(id);
	}

	public List<RestMethod> findByExample(RestMethod exampleRestMethod) {
		return restMethodAction.search(exampleRestMethod);
	}

	public void save(RestMethod restMethod) {
		restMethodAction.persist(restMethod);
	}

}
