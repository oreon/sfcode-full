package com.oreon.smartsis.websvc.exam;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.Choice;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.exam.ChoiceWebService", serviceName = "ChoiceWebService")
@Name("choiceWebService")
public class ChoiceWebServiceImpl implements ChoiceWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.exam.ChoiceAction choiceAction;

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
