package com.oreon.smartsis.websvc.exam;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.QuestionInstance;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.exam.QuestionInstanceWebService", serviceName = "QuestionInstanceWebService")
@Name("questionInstanceWebService")
public class QuestionInstanceWebServiceImpl
		implements
			QuestionInstanceWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.exam.QuestionInstanceAction questionInstanceAction;

	public QuestionInstance loadById(Long id) {
		return questionInstanceAction.loadFromId(id);
	}

	public List<QuestionInstance> findByExample(
			QuestionInstance exampleQuestionInstance) {
		return questionInstanceAction.search(exampleQuestionInstance);
	}

	public void save(QuestionInstance questionInstance) {
		questionInstanceAction.persist(questionInstance);
	}

}
