package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Grade;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.GradeWebService", serviceName = "GradeWebService")
@Name("gradeWebService")
public class GradeWebServiceImpl implements GradeWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.GradeAction gradeAction;

	public Grade loadById(Long id) {
		return gradeAction.loadFromId(id);
	}

	public List<Grade> findByExample(Grade exampleGrade) {
		return gradeAction.search(exampleGrade);
	}

	public void save(Grade grade) {
		gradeAction.persist(grade);
	}

}
