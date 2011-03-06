package com.oreon.smartsis.websvc.exam;

import javax.jws.WebService;
import com.oreon.smartsis.exam.ElectronicExamInstance;
import java.util.List;

@WebService
public interface ElectronicExamInstanceWebService {

	public ElectronicExamInstance loadById(Long id);

	public List<ElectronicExamInstance> findByExample(
			ElectronicExamInstance exampleElectronicExamInstance);

	public void save(ElectronicExamInstance electronicExamInstance);

	public Integer calculateScore(
			com.oreon.smartsis.exam.ElectronicExamInstance examInstance);

}
