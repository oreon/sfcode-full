package com.hrb.tservices.websvc.department;

import javax.jws.WebService;
import com.hrb.tservices.domain.department.Partner;
import java.util.List;

@WebService
public interface PartnerWebService {

	public Partner loadById(Long id);

	public List<Partner> findByExample(Partner examplePartner);

	public void save(Partner partner);

}
