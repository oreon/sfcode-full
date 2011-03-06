package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.GradeFee;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.GradeFeeWebService", serviceName = "GradeFeeWebService")
@Name("gradeFeeWebService")
public class GradeFeeWebServiceImpl implements GradeFeeWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.GradeFeeAction gradeFeeAction;

	public GradeFee loadById(Long id) {
		return gradeFeeAction.loadFromId(id);
	}

	public List<GradeFee> findByExample(GradeFee exampleGradeFee) {
		return gradeFeeAction.search(exampleGradeFee);
	}

	public void save(GradeFee gradeFee) {
		gradeFeeAction.persist(gradeFee);
	}

}
