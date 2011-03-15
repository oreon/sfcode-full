package com.pwc.insuranceclaims.websvc.quickclaim;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.quickclaim.Dependent;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.quickclaim.DependentWebService", serviceName = "DependentWebService")
@Name("dependentWebService")
public class DependentWebServiceImpl implements DependentWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.quickclaim.DependentAction dependentAction;

	public Dependent loadById(Long id) {
		return dependentAction.loadFromId(id);
	}

	public List<Dependent> findByExample(Dependent exampleDependent) {
		return dependentAction.search(exampleDependent);
	}

	public void save(Dependent dependent) {
		dependentAction.persist(dependent);
	}

}
