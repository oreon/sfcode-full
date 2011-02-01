package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.GradeSubject;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.GradeSubjectWebService", serviceName = "GradeSubjectWebService")
public class GradeSubjectWebServiceImpl implements GradeSubjectWebService {

	public GradeSubject loadById(Long id) {

		return null;
	}

	public List<GradeSubject> findByExample(GradeSubject exampleGradeSubject) {
		return null;
	}

	public void save(GradeSubject gradeSubject) {
	}
}
