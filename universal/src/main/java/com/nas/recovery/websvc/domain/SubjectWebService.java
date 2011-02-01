package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.Subject;
import java.util.List;

@WebService
public interface SubjectWebService {

	public Subject loadById(Long id);

	public List<Subject> findByExample(Subject exampleSubject);

	public void save(Subject subject);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
