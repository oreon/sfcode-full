package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.GradeSubject;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.GradeSubjectWebService", serviceName = "GradeSubjectWebService")
@Name("gradeSubjectWebService")
public class GradeSubjectWebServiceImpl implements GradeSubjectWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.GradeSubjectAction gradeSubjectAction;

	public GradeSubject loadById(Long id) {
		return gradeSubjectAction.loadFromId(id);
	}

	public List<GradeSubject> findByExample(GradeSubject exampleGradeSubject) {
		return gradeSubjectAction.search(exampleGradeSubject);
	}

	public void save(GradeSubject gradeSubject) {
		gradeSubjectAction.persist(gradeSubject);
	}

}
