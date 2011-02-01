package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Sponsor;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.SponsorWebService", serviceName = "SponsorWebService")
public class SponsorWebServiceImpl implements SponsorWebService {

	public Sponsor loadById(Long id) {

		return null;
	}

	public List<Sponsor> findByExample(Sponsor exampleSponsor) {
		return null;
	}

	public void save(Sponsor sponsor) {
	}
}
