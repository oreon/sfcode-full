package com.pwc.insuranceclaims.websvc.quickclaim;

import javax.jws.WebService;
import com.pwc.insuranceclaims.quickclaim.Dependent;
import java.util.List;

@WebService
public interface DependentWebService {

	public Dependent loadById(Long id);

	public List<Dependent> findByExample(Dependent exampleDependent);

	public void save(Dependent dependent);

}
