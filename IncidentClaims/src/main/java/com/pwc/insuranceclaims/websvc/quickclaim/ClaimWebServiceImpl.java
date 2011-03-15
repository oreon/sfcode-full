package com.pwc.insuranceclaims.websvc.quickclaim;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.quickclaim.Claim;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.quickclaim.ClaimWebService", serviceName = "ClaimWebService")
@Name("claimWebService")
public class ClaimWebServiceImpl implements ClaimWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.quickclaim.ClaimAction claimAction;

	public Claim loadById(Long id) {
		return claimAction.loadFromId(id);
	}

	public List<Claim> findByExample(Claim exampleClaim) {
		return claimAction.search(exampleClaim);
	}

	public void save(Claim claim) {
		claimAction.persist(claim);
	}

}
