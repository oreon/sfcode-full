package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.GradeFeesInstance;
import java.util.List;

@WebService
public interface GradeFeesInstanceWebService {

	public GradeFeesInstance loadById(Long id);

	public List<GradeFeesInstance> findByExample(
			GradeFeesInstance exampleGradeFeesInstance);

	public void save(GradeFeesInstance gradeFeesInstance);

}
