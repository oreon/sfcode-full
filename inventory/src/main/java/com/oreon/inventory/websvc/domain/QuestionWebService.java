package com.oreon.inventory.websvc.domain;

import javax.jws.WebService;
import com.oreon.inventory.domain.Question;
import java.util.List;

@WebService
public interface QuestionWebService {

	public Question loadById(Long id);

	public List<Question> findByExample(Question exampleQuestion);

	public void save(Question question);

}
