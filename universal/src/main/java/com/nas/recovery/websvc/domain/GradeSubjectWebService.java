package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.GradeSubject;
import java.util.List;

@WebService
public interface GradeSubjectWebService {

	public GradeSubject loadById(Long id);

	public List<GradeSubject> findByExample(GradeSubject exampleGradeSubject);

	public void save(GradeSubject gradeSubject);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
