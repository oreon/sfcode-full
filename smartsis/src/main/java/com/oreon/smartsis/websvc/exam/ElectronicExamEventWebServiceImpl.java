package com.oreon.smartsis.websvc.exam;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.ElectronicExamEvent;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.exam.ElectronicExamEventWebService", serviceName = "ElectronicExamEventWebService")
@Name("electronicExamEventWebService")
public class ElectronicExamEventWebServiceImpl
		implements
			ElectronicExamEventWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.exam.ElectronicExamEventAction electronicExamEventAction;

	public ElectronicExamEvent loadById(Long id) {
		return electronicExamEventAction.loadFromId(id);
	}

	public List<ElectronicExamEvent> findByExample(
			ElectronicExamEvent exampleElectronicExamEvent) {
		return electronicExamEventAction.search(exampleElectronicExamEvent);
	}

	public void save(ElectronicExamEvent electronicExamEvent) {
		electronicExamEventAction.persist(electronicExamEvent);
	}

}
