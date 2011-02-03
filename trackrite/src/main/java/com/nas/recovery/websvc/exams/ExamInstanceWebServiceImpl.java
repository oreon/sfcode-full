package com.nas.recovery.websvc.exams;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.exams.ExamInstance;

@WebService(endpointInterface = "com.nas.recovery.websvc.exams.ExamInstanceWebService", serviceName = "ExamInstanceWebService")
@Name("examInstanceWebService")
public class ExamInstanceWebServiceImpl implements ExamInstanceWebService {

	@In(create = true)
	com.nas.recovery.web.action.exams.ExamInstanceAction examInstanceAction;

	public ExamInstance loadById(Long id) {
		return examInstanceAction.loadFromId(id);
	}

	public List<ExamInstance> findByExample(ExamInstance exampleExamInstance) {
		return examInstanceAction.search(exampleExamInstance);
	}

	public void save(ExamInstance examInstance) {
		examInstanceAction.persist(examInstance);
	}

}
