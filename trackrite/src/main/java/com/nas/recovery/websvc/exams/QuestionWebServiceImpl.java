package com.nas.recovery.websvc.exams;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.exams.Question;

@WebService(endpointInterface = "com.nas.recovery.websvc.exams.QuestionWebService", serviceName = "QuestionWebService")
@Name("questionWebService")
public class QuestionWebServiceImpl implements QuestionWebService {

	@In(create = true)
	com.nas.recovery.web.action.exams.QuestionAction questionAction;

	public Question loadById(Long id) {
		return questionAction.loadFromId(id);
	}

	public List<Question> findByExample(Question exampleQuestion) {
		return questionAction.search(exampleQuestion);
	}

	public void save(Question question) {
		questionAction.persist(question);
	}

}
