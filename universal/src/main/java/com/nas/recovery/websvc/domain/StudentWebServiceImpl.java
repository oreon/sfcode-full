package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Student;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.StudentWebService", serviceName = "StudentWebService")
public class StudentWebServiceImpl implements StudentWebService {

	public Student loadById(Long id) {

		return null;
	}

	public List<Student> findByExample(Student exampleStudent) {
		return null;
	}

	public void save(Student student) {
	}
}
