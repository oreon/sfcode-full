package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.StudentVitalInfo;
import java.util.List;

@WebService
public interface StudentVitalInfoWebService {

	public StudentVitalInfo loadById(Long id);

	public List<StudentVitalInfo> findByExample(
			StudentVitalInfo exampleStudentVitalInfo);

	public void save(StudentVitalInfo studentVitalInfo);

}
