package com.nas.recovery.websvc.exams;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.exams.Exam;

@WebService(endpointInterface = "com.nas.recovery.websvc.exams.ExamWebService", serviceName = "ExamWebService")
@Name("examWebService")
public class ExamWebServiceImpl implements ExamWebService {

	@In(create = true)
	com.nas.recovery.web.action.exams.ExamAction examAction;

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
