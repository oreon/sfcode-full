package com.hrb.tservices.websvc.department;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.department.Partner;

@WebService(endpointInterface = "com.hrb.tservices.websvc.department.PartnerWebService", serviceName = "PartnerWebService")
@Name("partnerWebService")
public class PartnerWebServiceImpl implements PartnerWebService {

	@In(create = true)
	com.hrb.tservices.web.action.department.PartnerAction partnerAction;

	public Partner loadById(Long id) {
		return partnerAction.loadFromId(id);
	}

	public List<Partner> findByExample(Partner examplePartner) {
		return partnerAction.search(examplePartner);
	}

	public void save(Partner partner) {
		partnerAction.persist(partner);
	}

}
