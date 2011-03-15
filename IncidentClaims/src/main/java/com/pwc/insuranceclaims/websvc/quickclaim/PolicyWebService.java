package com.pwc.insuranceclaims.websvc.quickclaim;

import javax.jws.WebService;
import com.pwc.insuranceclaims.quickclaim.Policy;
import java.util.List;

@WebService
public interface PolicyWebService {

	public Policy loadById(Long id);

	public List<Policy> findByExample(Policy examplePolicy);

	public void save(Policy policy);

}
