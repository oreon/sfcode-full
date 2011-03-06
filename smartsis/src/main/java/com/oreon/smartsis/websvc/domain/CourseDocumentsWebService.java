package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.CourseDocuments;
import java.util.List;

@WebService
public interface CourseDocumentsWebService {

	public CourseDocuments loadById(Long id);

	public List<CourseDocuments> findByExample(
			CourseDocuments exampleCourseDocuments);

	public void save(CourseDocuments courseDocuments);

}
