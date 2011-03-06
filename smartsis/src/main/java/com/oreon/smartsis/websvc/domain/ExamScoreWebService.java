package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.ExamScore;
import java.util.List;

@WebService
public interface ExamScoreWebService {

	public ExamScore loadById(Long id);

	public List<ExamScore> findByExample(ExamScore exampleExamScore);

	public void save(ExamScore examScore);

}
