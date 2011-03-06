package com.oreon.smartsis.websvc.exam;

import javax.jws.WebService;
import com.oreon.smartsis.exam.Choice;
import java.util.List;

@WebService
public interface ChoiceWebService {

	public Choice loadById(Long id);

	public List<Choice> findByExample(Choice exampleChoice);

	public void save(Choice choice);

}
