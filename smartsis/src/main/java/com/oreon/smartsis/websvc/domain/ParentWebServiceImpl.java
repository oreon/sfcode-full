package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Parent;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.ParentWebService", serviceName = "ParentWebService")
@Name("parentWebService")
public class ParentWebServiceImpl implements ParentWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.ParentAction parentAction;

	public Parent loadById(Long id) {
		return parentAction.loadFromId(id);
	}

	public List<Parent> findByExample(Parent exampleParent) {
		return parentAction.search(exampleParent);
	}

	public void save(Parent parent) {
		parentAction.persist(parent);
	}

}
