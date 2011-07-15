package com.oreon.inventory.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.inventory.domain.Exam;

@WebService(endpointInterface = "com.oreon.inventory.websvc.domain.ExamWebService", serviceName = "ExamWebService")
@Name("examWebService")
public class ExamWebServiceImpl implements ExamWebService {

	@In(create = true)
	com.oreon.inventory.web.action.domain.ExamAction examAction;

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
