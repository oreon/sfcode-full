package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Student;
import java.util.List;

@WebService
public interface StudentWebService {

	public Student loadById(Long id);

	public List<Student> findByExample(Student exampleStudent);

	public void save(Student student);

}
