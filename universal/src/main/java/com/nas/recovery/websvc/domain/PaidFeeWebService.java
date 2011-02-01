package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.PaidFee;
import java.util.List;

@WebService
public interface PaidFeeWebService {

	public PaidFee loadById(Long id);

	public List<PaidFee> findByExample(PaidFee examplePaidFee);

	public void save(PaidFee paidFee);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
