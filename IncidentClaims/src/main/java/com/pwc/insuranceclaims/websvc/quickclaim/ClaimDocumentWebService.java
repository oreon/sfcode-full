package com.pwc.insuranceclaims.websvc.quickclaim;

import javax.jws.WebService;
import com.pwc.insuranceclaims.quickclaim.ClaimDocument;
import java.util.List;

@WebService
public interface ClaimDocumentWebService {

	public ClaimDocument loadById(Long id);

	public List<ClaimDocument> findByExample(ClaimDocument exampleClaimDocument);

	public void save(ClaimDocument claimDocument);

}
