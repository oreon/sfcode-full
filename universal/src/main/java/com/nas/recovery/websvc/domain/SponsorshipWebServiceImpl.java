package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Sponsorship;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.SponsorshipWebService", serviceName = "SponsorshipWebService")
public class SponsorshipWebServiceImpl implements SponsorshipWebService {

	public Sponsorship loadById(Long id) {

		return null;
	}

	public List<Sponsorship> findByExample(Sponsorship exampleSponsorship) {
		return null;
	}

	public void save(Sponsorship sponsorship) {
	}
}
