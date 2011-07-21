package com.oreon.smartsis.websvc.fees;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.fees.GradeFeeItem;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.fees.GradeFeeItemWebService", serviceName = "GradeFeeItemWebService")
@Name("gradeFeeItemWebService")
public class GradeFeeItemWebServiceImpl implements GradeFeeItemWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.fees.GradeFeeItemAction gradeFeeItemAction;

	public GradeFeeItem loadById(Long id) {
		return gradeFeeItemAction.loadFromId(id);
	}

	public List<GradeFeeItem> findByExample(GradeFeeItem exampleGradeFeeItem) {
		return gradeFeeItemAction.search(exampleGradeFeeItem);
	}

	public void save(GradeFeeItem gradeFeeItem) {
		gradeFeeItemAction.persist(gradeFeeItem);
	}

}
