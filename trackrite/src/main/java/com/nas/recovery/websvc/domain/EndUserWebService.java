package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import org.wc.trackrite.domain.EndUser;
import java.util.List;

@WebService
public interface EndUserWebService {

	public EndUser loadById(Long id);

	public List<EndUser> findByExample(EndUser exampleEndUser);

	public void save(EndUser endUser);

}
