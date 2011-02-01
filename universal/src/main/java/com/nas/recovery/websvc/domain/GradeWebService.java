package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.Grade;
import java.util.List;

@WebService
public interface GradeWebService {

	public Grade loadById(Long id);

	public List<Grade> findByExample(Grade exampleGrade);

	public void save(Grade grade);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
