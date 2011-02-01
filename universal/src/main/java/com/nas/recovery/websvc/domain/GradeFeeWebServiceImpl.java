package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.GradeFee;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.GradeFeeWebService", serviceName = "GradeFeeWebService")
public class GradeFeeWebServiceImpl implements GradeFeeWebService {

	public GradeFee loadById(Long id) {

		return null;
	}

	public List<GradeFee> findByExample(GradeFee exampleGradeFee) {
		return null;
	}

	public void save(GradeFee gradeFee) {
	}
}
