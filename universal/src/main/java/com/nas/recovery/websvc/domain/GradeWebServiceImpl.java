package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Grade;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.GradeWebService", serviceName = "GradeWebService")
public class GradeWebServiceImpl implements GradeWebService {

	public Grade loadById(Long id) {

		return null;
	}

	public List<Grade> findByExample(Grade exampleGrade) {
		return null;
	}

	public void save(Grade grade) {
	}
}
