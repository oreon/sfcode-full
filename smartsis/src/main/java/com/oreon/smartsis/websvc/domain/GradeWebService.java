package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Grade;
import java.util.List;

@WebService
public interface GradeWebService {

	public Grade loadById(Long id);

	public List<Grade> findByExample(Grade exampleGrade);

	public void save(Grade grade);

}
