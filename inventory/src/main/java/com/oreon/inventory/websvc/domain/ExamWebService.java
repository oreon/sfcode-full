package com.oreon.inventory.websvc.domain;

import javax.jws.WebService;
import com.oreon.inventory.domain.Exam;
import java.util.List;

@WebService
public interface ExamWebService {

	public Exam loadById(Long id);

	public List<Exam> findByExample(Exam exampleExam);

	public void save(Exam exam);

}
