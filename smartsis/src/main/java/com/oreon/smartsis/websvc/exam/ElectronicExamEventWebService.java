package com.oreon.smartsis.websvc.exam;

import javax.jws.WebService;
import com.oreon.smartsis.exam.ElectronicExamEvent;
import java.util.List;

@WebService
public interface ElectronicExamEventWebService {

	public ElectronicExamEvent loadById(Long id);

	public List<ElectronicExamEvent> findByExample(
			ElectronicExamEvent exampleElectronicExamEvent);

	public void save(ElectronicExamEvent electronicExamEvent);

}
