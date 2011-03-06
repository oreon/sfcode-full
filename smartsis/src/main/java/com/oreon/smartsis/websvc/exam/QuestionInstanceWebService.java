package com.oreon.smartsis.websvc.exam;

import javax.jws.WebService;
import com.oreon.smartsis.exam.QuestionInstance;
import java.util.List;

@WebService
public interface QuestionInstanceWebService {

	public QuestionInstance loadById(Long id);

	public List<QuestionInstance> findByExample(
			QuestionInstance exampleQuestionInstance);

	public void save(QuestionInstance questionInstance);

}
