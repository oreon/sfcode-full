package com.nas.recovery.websvc.exams;

import javax.jws.WebService;
import org.wc.trackrite.exams.Choice;
import java.util.List;

@WebService
public interface ChoiceWebService {

	public Choice loadById(Long id);

	public List<Choice> findByExample(Choice exampleChoice);

	public void save(Choice choice);

}
