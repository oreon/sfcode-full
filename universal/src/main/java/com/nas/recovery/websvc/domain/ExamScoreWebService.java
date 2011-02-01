package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.ExamScore;
import java.util.List;

@WebService
public interface ExamScoreWebService {

	public ExamScore loadById(Long id);

	public List<ExamScore> findByExample(ExamScore exampleExamScore);

	public void save(ExamScore examScore);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
