package com.oreon.smartsis.websvc.exam;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.ElectronicExam;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.exam.ElectronicExamWebService", serviceName = "ElectronicExamWebService")
@Name("electronicExamWebService")
public class ElectronicExamWebServiceImpl implements ElectronicExamWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.exam.ElectronicExamAction electronicExamAction;

	public ElectronicExam loadById(Long id) {
		return electronicExamAction.loadFromId(id);
	}

	public List<ElectronicExam> findByExample(
			ElectronicExam exampleElectronicExam) {
		return electronicExamAction.search(exampleElectronicExam);
	}

	public void save(ElectronicExam electronicExam) {
		electronicExamAction.persist(electronicExam);
	}

}
