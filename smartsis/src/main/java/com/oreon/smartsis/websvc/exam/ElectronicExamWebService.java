package com.oreon.smartsis.websvc.exam;

import javax.jws.WebService;
import com.oreon.smartsis.exam.ElectronicExam;
import java.util.List;

@WebService
public interface ElectronicExamWebService {

	public ElectronicExam loadById(Long id);

	public List<ElectronicExam> findByExample(
			ElectronicExam exampleElectronicExam);

	public void save(ElectronicExam electronicExam);

}
