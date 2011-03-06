package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Sponsor;
import java.util.List;

@WebService
public interface SponsorWebService {

	public Sponsor loadById(Long id);

	public List<Sponsor> findByExample(Sponsor exampleSponsor);

	public void save(Sponsor sponsor);

}
