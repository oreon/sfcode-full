package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.StudentVitalInfo;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.StudentVitalInfoWebService", serviceName = "StudentVitalInfoWebService")
@Name("studentVitalInfoWebService")
public class StudentVitalInfoWebServiceImpl
		implements
			StudentVitalInfoWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.StudentVitalInfoAction studentVitalInfoAction;

	public StudentVitalInfo loadById(Long id) {
		return studentVitalInfoAction.loadFromId(id);
	}

	public List<StudentVitalInfo> findByExample(
			StudentVitalInfo exampleStudentVitalInfo) {
		return studentVitalInfoAction.search(exampleStudentVitalInfo);
	}

	public void save(StudentVitalInfo studentVitalInfo) {
		studentVitalInfoAction.persist(studentVitalInfo);
	}

}
