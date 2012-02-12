package com.hrb.tservices.websvc.metrics;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.metrics.ClientType;

@WebService(endpointInterface = "com.hrb.tservices.websvc.metrics.ClientTypeWebService", serviceName = "ClientTypeWebService")
@Name("clientTypeWebService")
public class ClientTypeWebServiceImpl implements ClientTypeWebService {

	@In(create = true)
	com.hrb.tservices.web.action.metrics.ClientTypeAction clientTypeAction;

	public ClientType loadById(Long id) {
		return clientTypeAction.loadFromId(id);
	}

	public List<ClientType> findByExample(ClientType exampleClientType) {
		return clientTypeAction.search(exampleClientType);
	}

	public void save(ClientType clientType) {
		clientTypeAction.persist(clientType);
	}

}
