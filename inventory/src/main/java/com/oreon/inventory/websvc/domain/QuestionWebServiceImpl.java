package com.oreon.inventory.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.inventory.domain.Question;

@WebService(endpointInterface = "com.oreon.inventory.websvc.domain.QuestionWebService", serviceName = "QuestionWebService")
@Name("questionWebService")
public class QuestionWebServiceImpl implements QuestionWebService {

	@In(create = true)
	com.oreon.inventory.web.action.domain.QuestionAction questionAction;

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
