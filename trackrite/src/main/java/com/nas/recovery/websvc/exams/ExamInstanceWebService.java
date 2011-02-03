package com.nas.recovery.websvc.exams;

import javax.jws.WebService;
import org.wc.trackrite.exams.ExamInstance;
import java.util.List;

@WebService
public interface ExamInstanceWebService {

	public ExamInstance loadById(Long id);

	public List<ExamInstance> findByExample(ExamInstance exampleExamInstance);

	public void save(ExamInstance examInstance);

}
