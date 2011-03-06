package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.PaidFee;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.PaidFeeWebService", serviceName = "PaidFeeWebService")
@Name("paidFeeWebService")
public class PaidFeeWebServiceImpl implements PaidFeeWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.PaidFeeAction paidFeeAction;

	public PaidFee loadById(Long id) {
		return paidFeeAction.loadFromId(id);
	}

	public List<PaidFee> findByExample(PaidFee examplePaidFee) {
		return paidFeeAction.search(examplePaidFee);
	}

	public void save(PaidFee paidFee) {
		paidFeeAction.persist(paidFee);
	}

}
