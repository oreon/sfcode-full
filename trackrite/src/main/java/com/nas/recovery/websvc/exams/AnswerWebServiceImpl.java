package com.nas.recovery.websvc.exams;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.exams.Answer;

@WebService(endpointInterface = "com.nas.recovery.websvc.exams.AnswerWebService", serviceName = "AnswerWebService")
@Name("answerWebService")
public class AnswerWebServiceImpl implements AnswerWebService {

	@In(create = true)
	com.nas.recovery.web.action.exams.AnswerAction answerAction;

	public Answer loadById(Long id) {
		return answerAction.loadFromId(id);
	}

	public List<Answer> findByExample(Answer exampleAnswer) {
		return answerAction.search(exampleAnswer);
	}

	public void save(Answer answer) {
		answerAction.persist(answer);
	}

}
