package com.oreon.smartsis.websvc.fees;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.fees.FeeItem;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.fees.FeeItemWebService", serviceName = "FeeItemWebService")
@Name("feeItemWebService")
public class FeeItemWebServiceImpl implements FeeItemWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.fees.FeeItemAction feeItemAction;

	public FeeItem loadById(Long id) {
		return feeItemAction.loadFromId(id);
	}

	public List<FeeItem> findByExample(FeeItem exampleFeeItem) {
		return feeItemAction.search(exampleFeeItem);
	}

	public void save(FeeItem feeItem) {
		feeItemAction.persist(feeItem);
	}

}
