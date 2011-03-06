package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.ExamInstance;
import java.util.List;

@WebService
public interface ExamInstanceWebService {

	public ExamInstance loadById(Long id);

	public List<ExamInstance> findByExample(ExamInstance exampleExamInstance);

	public void save(ExamInstance examInstance);

}
