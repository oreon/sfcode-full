package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.PaidFee;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.PaidFeeWebService", serviceName = "PaidFeeWebService")
public class PaidFeeWebServiceImpl implements PaidFeeWebService {

	public PaidFee loadById(Long id) {

		return null;
	}

	public List<PaidFee> findByExample(PaidFee examplePaidFee) {
		return null;
	}

	public void save(PaidFee paidFee) {
	}
}
