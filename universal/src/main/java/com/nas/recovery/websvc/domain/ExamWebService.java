package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.Exam;
import java.util.List;

@WebService
public interface ExamWebService {

	public Exam loadById(Long id);

	public List<Exam> findByExample(Exam exampleExam);

	public void save(Exam exam);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
