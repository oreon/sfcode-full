package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.ExamScore;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.ExamScoreWebService", serviceName = "ExamScoreWebService")
public class ExamScoreWebServiceImpl implements ExamScoreWebService {

	public ExamScore loadById(Long id) {

		return null;
	}

	public List<ExamScore> findByExample(ExamScore exampleExamScore) {
		return null;
	}

	public void save(ExamScore examScore) {
	}
}
