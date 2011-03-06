package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.ExamScore;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.ExamScoreWebService", serviceName = "ExamScoreWebService")
@Name("examScoreWebService")
public class ExamScoreWebServiceImpl implements ExamScoreWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.ExamScoreAction examScoreAction;

	public ExamScore loadById(Long id) {
		return examScoreAction.loadFromId(id);
	}

	public List<ExamScore> findByExample(ExamScore exampleExamScore) {
		return examScoreAction.search(exampleExamScore);
	}

	public void save(ExamScore examScore) {
		examScoreAction.persist(examScore);
	}

}
