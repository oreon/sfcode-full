package com.oreon.smartsis.websvc.exam;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.ElectronicExamInstance;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.exam.ElectronicExamInstanceWebService", serviceName = "ElectronicExamInstanceWebService")
@Name("electronicExamInstanceWebService")
public class ElectronicExamInstanceWebServiceImpl
		implements
			ElectronicExamInstanceWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.exam.ElectronicExamInstanceAction electronicExamInstanceAction;

	public ElectronicExamInstance loadById(Long id) {
		return electronicExamInstanceAction.loadFromId(id);
	}

	public List<ElectronicExamInstance> findByExample(
			ElectronicExamInstance exampleElectronicExamInstance) {
		return electronicExamInstanceAction
				.search(exampleElectronicExamInstance);
	}

	public void save(ElectronicExamInstance electronicExamInstance) {
		electronicExamInstanceAction.persist(electronicExamInstance);
	}

	public Integer calculateScore(
			com.oreon.smartsis.exam.ElectronicExamInstance examInstance) {
		return electronicExamInstanceAction.calculateScore(examInstance);
	}

}
