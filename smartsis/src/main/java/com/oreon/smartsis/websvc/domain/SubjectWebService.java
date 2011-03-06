package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Subject;
import java.util.List;

@WebService
public interface SubjectWebService {

	public Subject loadById(Long id);

	public List<Subject> findByExample(Subject exampleSubject);

	public void save(Subject subject);

}
