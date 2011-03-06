package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Subject;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.SubjectWebService", serviceName = "SubjectWebService")
@Name("subjectWebService")
public class SubjectWebServiceImpl implements SubjectWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.SubjectAction subjectAction;

	public Subject loadById(Long id) {
		return subjectAction.loadFromId(id);
	}

	public List<Subject> findByExample(Subject exampleSubject) {
		return subjectAction.search(exampleSubject);
	}

	public void save(Subject subject) {
		subjectAction.persist(subject);
	}

}
