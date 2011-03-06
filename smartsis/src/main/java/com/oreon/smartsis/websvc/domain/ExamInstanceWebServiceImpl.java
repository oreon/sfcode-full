package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.ExamInstance;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.ExamInstanceWebService", serviceName = "ExamInstanceWebService")
@Name("examInstanceWebService")
public class ExamInstanceWebServiceImpl implements ExamInstanceWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.ExamInstanceAction examInstanceAction;

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
