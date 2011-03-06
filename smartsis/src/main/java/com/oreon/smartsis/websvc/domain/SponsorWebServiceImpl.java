package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Sponsor;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.SponsorWebService", serviceName = "SponsorWebService")
@Name("sponsorWebService")
public class SponsorWebServiceImpl implements SponsorWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.SponsorAction sponsorAction;

	public Sponsor loadById(Long id) {
		return sponsorAction.loadFromId(id);
	}

	public List<Sponsor> findByExample(Sponsor exampleSponsor) {
		return sponsorAction.search(exampleSponsor);
	}

	public void save(Sponsor sponsor) {
		sponsorAction.persist(sponsor);
	}

}
