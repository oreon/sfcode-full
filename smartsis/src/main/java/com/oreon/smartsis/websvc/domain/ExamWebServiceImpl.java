package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Exam;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.ExamWebService", serviceName = "ExamWebService")
@Name("examWebService")
public class ExamWebServiceImpl implements ExamWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.ExamAction examAction;

	public Exam loadById(Long id) {
		return examAction.loadFromId(id);
	}

	public List<Exam> findByExample(Exam exampleExam) {
		return examAction.search(exampleExam);
	}

	public void save(Exam exam) {
		examAction.persist(exam);
	}

}
