package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.ExamInstance;
import java.util.List;

@WebService
public interface ExamInstanceWebService {

	public ExamInstance loadById(Long id);

	public List<ExamInstance> findByExample(ExamInstance exampleExamInstance);

	public void save(ExamInstance examInstance);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
