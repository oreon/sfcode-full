package com.pwc.insuranceclaims.websvc.quickclaim;

import javax.jws.WebService;
import com.pwc.insuranceclaims.quickclaim.Claim;
import java.util.List;

@WebService
public interface ClaimWebService {

	public Claim loadById(Long id);

	public List<Claim> findByExample(Claim exampleClaim);

	public void save(Claim claim);

}
