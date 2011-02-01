package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.ExamInstance;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.ExamInstanceWebService", serviceName = "ExamInstanceWebService")
public class ExamInstanceWebServiceImpl implements ExamInstanceWebService {

	public ExamInstance loadById(Long id) {

		return null;
	}

	public List<ExamInstance> findByExample(ExamInstance exampleExamInstance) {
		return null;
	}

	public void save(ExamInstance examInstance) {
	}
}
