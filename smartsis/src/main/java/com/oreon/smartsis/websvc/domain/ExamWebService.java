package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Exam;
import java.util.List;

@WebService
public interface ExamWebService {

	public Exam loadById(Long id);

	public List<Exam> findByExample(Exam exampleExam);

	public void save(Exam exam);

}
