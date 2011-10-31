package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Student;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.StudentWebService", serviceName = "StudentWebService")
@Name("studentWebService")
public class StudentWebServiceImpl implements StudentWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.StudentAction studentAction;

	public Student loadById(Long id) {
		return studentAction.loadFromId(id);
	}

	public List<Student> findByExample(Student exampleStudent) {
		return studentAction.search(exampleStudent);
	}

	public void save(Student student) {
		studentAction.persist(student);
	}

	public List<com.oreon.smartsis.exam.ElectronicExamInstance> eExamsForStudent() {
		return null;
	}

}
