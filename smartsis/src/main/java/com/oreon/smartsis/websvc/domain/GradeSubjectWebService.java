package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.GradeSubject;
import java.util.List;

@WebService
public interface GradeSubjectWebService {

	public GradeSubject loadById(Long id);

	public List<GradeSubject> findByExample(GradeSubject exampleGradeSubject);

	public void save(GradeSubject gradeSubject);

}
