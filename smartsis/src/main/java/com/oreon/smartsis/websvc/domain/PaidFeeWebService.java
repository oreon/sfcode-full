package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.PaidFee;
import java.util.List;

@WebService
public interface PaidFeeWebService {

	public PaidFee loadById(Long id);

	public List<PaidFee> findByExample(PaidFee examplePaidFee);

	public void save(PaidFee paidFee);

}
