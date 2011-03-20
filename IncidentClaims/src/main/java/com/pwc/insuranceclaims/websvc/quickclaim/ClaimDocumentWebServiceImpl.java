package com.pwc.insuranceclaims.websvc.quickclaim;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.quickclaim.ClaimDocument;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.quickclaim.ClaimDocumentWebService", serviceName = "ClaimDocumentWebService")
@Name("claimDocumentWebService")
public class ClaimDocumentWebServiceImpl implements ClaimDocumentWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.quickclaim.ClaimDocumentAction claimDocumentAction;

	public ClaimDocument loadById(Long id) {
		return claimDocumentAction.loadFromId(id);
	}

	public List<ClaimDocument> findByExample(ClaimDocument exampleClaimDocument) {
		return claimDocumentAction.search(exampleClaimDocument);
	}

	public void save(ClaimDocument claimDocument) {
		claimDocumentAction.persist(claimDocument);
	}

}
