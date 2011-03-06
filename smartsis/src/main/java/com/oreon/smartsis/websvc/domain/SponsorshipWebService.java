package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Sponsorship;
import java.util.List;

@WebService
public interface SponsorshipWebService {

	public Sponsorship loadById(Long id);

	public List<Sponsorship> findByExample(Sponsorship exampleSponsorship);

	public void save(Sponsorship sponsorship);

}
