package com.oreon.smartsis.websvc.exam;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.Question;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.exam.QuestionWebService", serviceName = "QuestionWebService")
@Name("questionWebService")
public class QuestionWebServiceImpl implements QuestionWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.exam.QuestionAction questionAction;

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
