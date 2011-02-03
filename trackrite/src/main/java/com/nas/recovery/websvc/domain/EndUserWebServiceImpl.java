package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.domain.EndUser;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.EndUserWebService", serviceName = "EndUserWebService")
@Name("endUserWebService")
public class EndUserWebServiceImpl implements EndUserWebService {

	@In(create = true)
	com.nas.recovery.web.action.domain.EndUserAction endUserAction;

	public EndUser loadById(Long id) {
		return endUserAction.loadFromId(id);
	}

	public List<EndUser> findByExample(EndUser exampleEndUser) {
		return endUserAction.search(exampleEndUser);
	}

	public void save(EndUser endUser) {
		endUserAction.persist(endUser);
	}

}
