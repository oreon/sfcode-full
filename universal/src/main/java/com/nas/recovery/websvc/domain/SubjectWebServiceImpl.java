package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Subject;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.SubjectWebService", serviceName = "SubjectWebService")
public class SubjectWebServiceImpl implements SubjectWebService {

	public Subject loadById(Long id) {

		return null;
	}

	public List<Subject> findByExample(Subject exampleSubject) {
		return null;
	}

	public void save(Subject subject) {
	}
}
