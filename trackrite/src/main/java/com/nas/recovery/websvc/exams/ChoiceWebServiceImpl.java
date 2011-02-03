package com.nas.recovery.websvc.exams;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.exams.Choice;

@WebService(endpointInterface = "com.nas.recovery.websvc.exams.ChoiceWebService", serviceName = "ChoiceWebService")
@Name("choiceWebService")
public class ChoiceWebServiceImpl implements ChoiceWebService {

	@In(create = true)
	com.nas.recovery.web.action.exams.ChoiceAction choiceAction;

	public Choice loadById(Long id) {
		return choiceAction.loadFromId(id);
	}

	public List<Choice> findByExample(Choice exampleChoice) {
		return choiceAction.search(exampleChoice);
	}

	public void save(Choice choice) {
		choiceAction.persist(choice);
	}

}
