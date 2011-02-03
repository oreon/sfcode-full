package com.nas.recovery.websvc.exams;

import javax.jws.WebService;
import org.wc.trackrite.exams.Exam;
import java.util.List;

@WebService
public interface ExamWebService {

	public Exam loadById(Long id);

	public List<Exam> findByExample(Exam exampleExam);

	public void save(Exam exam);

}
