package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.Sponsor;
import java.util.List;

@WebService
public interface SponsorWebService {

	public Sponsor loadById(Long id);

	public List<Sponsor> findByExample(Sponsor exampleSponsor);

	public void save(Sponsor sponsor);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
