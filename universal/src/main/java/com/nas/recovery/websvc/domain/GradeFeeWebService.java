package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.GradeFee;
import java.util.List;

@WebService
public interface GradeFeeWebService {

	public GradeFee loadById(Long id);

	public List<GradeFee> findByExample(GradeFee exampleGradeFee);

	public void save(GradeFee gradeFee);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
