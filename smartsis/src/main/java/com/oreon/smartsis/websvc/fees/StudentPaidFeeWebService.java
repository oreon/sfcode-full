package com.oreon.smartsis.websvc.fees;

import javax.jws.WebService;
import com.oreon.smartsis.fees.StudentPaidFee;
import java.util.List;

@WebService
public interface StudentPaidFeeWebService {

	public StudentPaidFee loadById(Long id);

	public List<StudentPaidFee> findByExample(
			StudentPaidFee exampleStudentPaidFee);

	public void save(StudentPaidFee studentPaidFee);

}
