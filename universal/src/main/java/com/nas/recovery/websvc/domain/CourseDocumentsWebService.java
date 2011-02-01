package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.CourseDocuments;
import java.util.List;

@WebService
public interface CourseDocumentsWebService {

	public CourseDocuments loadById(Long id);

	public List<CourseDocuments> findByExample(
			CourseDocuments exampleCourseDocuments);

	public void save(CourseDocuments courseDocuments);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
