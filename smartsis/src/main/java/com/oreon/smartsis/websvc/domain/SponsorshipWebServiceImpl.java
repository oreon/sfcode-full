package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Sponsorship;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.SponsorshipWebService", serviceName = "SponsorshipWebService")
@Name("sponsorshipWebService")
public class SponsorshipWebServiceImpl implements SponsorshipWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.SponsorshipAction sponsorshipAction;

	public Sponsorship loadById(Long id) {
		return sponsorshipAction.loadFromId(id);
	}

	public List<Sponsorship> findByExample(Sponsorship exampleSponsorship) {
		return sponsorshipAction.search(exampleSponsorship);
	}

	public void save(Sponsorship sponsorship) {
		sponsorshipAction.persist(sponsorship);
	}

}
