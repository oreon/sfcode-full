package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.CourseDocuments;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.CourseDocumentsWebService", serviceName = "CourseDocumentsWebService")
@Name("courseDocumentsWebService")
public class CourseDocumentsWebServiceImpl implements CourseDocumentsWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.CourseDocumentsAction courseDocumentsAction;

	public CourseDocuments loadById(Long id) {
		return courseDocumentsAction.loadFromId(id);
	}

	public List<CourseDocuments> findByExample(
			CourseDocuments exampleCourseDocuments) {
		return courseDocumentsAction.search(exampleCourseDocuments);
	}

	public void save(CourseDocuments courseDocuments) {
		courseDocumentsAction.persist(courseDocuments);
	}

}
