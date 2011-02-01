package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Exam;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.ExamWebService", serviceName = "ExamWebService")
public class ExamWebServiceImpl implements ExamWebService {

	public Exam loadById(Long id) {

		return null;
	}

	public List<Exam> findByExample(Exam exampleExam) {
		return null;
	}

	public void save(Exam exam) {
	}
}
