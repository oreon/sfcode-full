package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.GradeFeesInstance;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.GradeFeesInstanceWebService", serviceName = "GradeFeesInstanceWebService")
@Name("gradeFeesInstanceWebService")
public class GradeFeesInstanceWebServiceImpl
		implements
			GradeFeesInstanceWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.GradeFeesInstanceAction gradeFeesInstanceAction;

	public GradeFeesInstance loadById(Long id) {
		return gradeFeesInstanceAction.loadFromId(id);
	}

	public List<GradeFeesInstance> findByExample(
			GradeFeesInstance exampleGradeFeesInstance) {
		return gradeFeesInstanceAction.search(exampleGradeFeesInstance);
	}

	public void save(GradeFeesInstance gradeFeesInstance) {
		gradeFeesInstanceAction.persist(gradeFeesInstance);
	}

}
