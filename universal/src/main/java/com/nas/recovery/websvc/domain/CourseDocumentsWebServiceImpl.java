package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.CourseDocuments;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.CourseDocumentsWebService", serviceName = "CourseDocumentsWebService")
public class CourseDocumentsWebServiceImpl implements CourseDocumentsWebService {

	public CourseDocuments loadById(Long id) {

		return null;
	}

	public List<CourseDocuments> findByExample(
			CourseDocuments exampleCourseDocuments) {
		return null;
	}

	public void save(CourseDocuments courseDocuments) {
	}
}
