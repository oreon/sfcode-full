package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Fee;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.FeeWebService", serviceName = "FeeWebService")
@Name("feeWebService")
public class FeeWebServiceImpl implements FeeWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.FeeAction feeAction;

	public Fee loadById(Long id) {
		return feeAction.loadFromId(id);
	}

	public List<Fee> findByExample(Fee exampleFee) {
		return feeAction.search(exampleFee);
	}

	public void save(Fee fee) {
		feeAction.persist(fee);
	}

}
