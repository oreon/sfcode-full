package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.DisciplinaryAction;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.DisciplinaryActionWebService", serviceName = "DisciplinaryActionWebService")
@Name("disciplinaryActionWebService")
public class DisciplinaryActionWebServiceImpl
		implements
			DisciplinaryActionWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.DisciplinaryActionAction disciplinaryActionAction;

	public DisciplinaryAction loadById(Long id) {
		return disciplinaryActionAction.loadFromId(id);
	}

	public List<DisciplinaryAction> findByExample(
			DisciplinaryAction exampleDisciplinaryAction) {
		return disciplinaryActionAction.search(exampleDisciplinaryAction);
	}

	public void save(DisciplinaryAction disciplinaryAction) {
		disciplinaryActionAction.persist(disciplinaryAction);
	}

}
