package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.Sponsorship;
import java.util.List;

@WebService
public interface SponsorshipWebService {

	public Sponsorship loadById(Long id);

	public List<Sponsorship> findByExample(Sponsorship exampleSponsorship);

	public void save(Sponsorship sponsorship);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
