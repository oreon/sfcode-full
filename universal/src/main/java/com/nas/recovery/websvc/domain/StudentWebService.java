package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.Student;
import java.util.List;

@WebService
public interface StudentWebService {

	public Student loadById(Long id);

	public List<Student> findByExample(Student exampleStudent);

	public void save(Student student);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
