package com.nas.recovery.websvc.exams;

import javax.jws.WebService;
import org.wc.trackrite.exams.Question;
import java.util.List;

@WebService
public interface QuestionWebService {

	public Question loadById(Long id);

	public List<Question> findByExample(Question exampleQuestion);

	public void save(Question question);

}
