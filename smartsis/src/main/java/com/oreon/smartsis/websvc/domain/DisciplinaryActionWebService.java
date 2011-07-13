package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.DisciplinaryAction;
import java.util.List;

@WebService
public interface DisciplinaryActionWebService {

	public DisciplinaryAction loadById(Long id);

	public List<DisciplinaryAction> findByExample(
			DisciplinaryAction exampleDisciplinaryAction);

	public void save(DisciplinaryAction disciplinaryAction);

}
