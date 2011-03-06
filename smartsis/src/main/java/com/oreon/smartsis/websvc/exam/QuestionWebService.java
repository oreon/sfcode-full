package com.oreon.smartsis.websvc.exam;

import javax.jws.WebService;
import com.oreon.smartsis.exam.Question;
import java.util.List;

@WebService
public interface QuestionWebService {

	public Question loadById(Long id);

	public List<Question> findByExample(Question exampleQuestion);

	public void save(Question question);

}
