package com.oreon.smartsis.websvc.fees;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.fees.StudentPaidFee;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.fees.StudentPaidFeeWebService", serviceName = "StudentPaidFeeWebService")
@Name("studentPaidFeeWebService")
public class StudentPaidFeeWebServiceImpl implements StudentPaidFeeWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.fees.StudentPaidFeeAction studentPaidFeeAction;

	public StudentPaidFee loadById(Long id) {
		return studentPaidFeeAction.loadFromId(id);
	}

	public List<StudentPaidFee> findByExample(
			StudentPaidFee exampleStudentPaidFee) {
		return studentPaidFeeAction.search(exampleStudentPaidFee);
	}

	public void save(StudentPaidFee studentPaidFee) {
		studentPaidFeeAction.persist(studentPaidFee);
	}

}
