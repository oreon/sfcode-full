package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Parent;
import java.util.List;

@WebService
public interface ParentWebService {

	public Parent loadById(Long id);

	public List<Parent> findByExample(Parent exampleParent);

	public void save(Parent parent);

}
