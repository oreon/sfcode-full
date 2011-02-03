package com.nas.recovery.websvc.exams;

import javax.jws.WebService;
import org.wc.trackrite.exams.Answer;
import java.util.List;

@WebService
public interface AnswerWebService {

	public Answer loadById(Long id);

	public List<Answer> findByExample(Answer exampleAnswer);

	public void save(Answer answer);

}
