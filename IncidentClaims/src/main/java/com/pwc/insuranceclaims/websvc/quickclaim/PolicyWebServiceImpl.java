package com.pwc.insuranceclaims.websvc.quickclaim;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.quickclaim.Policy;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.quickclaim.PolicyWebService", serviceName = "PolicyWebService")
@Name("policyWebService")
public class PolicyWebServiceImpl implements PolicyWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.quickclaim.PolicyAction policyAction;

	public Policy loadById(Long id) {
		return policyAction.loadFromId(id);
	}

	public List<Policy> findByExample(Policy examplePolicy) {
		return policyAction.search(examplePolicy);
	}

	public void save(Policy policy) {
		policyAction.persist(policy);
	}

}
