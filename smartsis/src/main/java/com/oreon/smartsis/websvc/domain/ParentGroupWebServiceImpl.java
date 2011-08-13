package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.ParentGroup;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.ParentGroupWebService", serviceName = "ParentGroupWebService")
@Name("parentGroupWebService")
public class ParentGroupWebServiceImpl implements ParentGroupWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.ParentGroupAction parentGroupAction;

	public ParentGroup loadById(Long id) {
		return parentGroupAction.loadFromId(id);
	}

	public List<ParentGroup> findByExample(ParentGroup exampleParentGroup) {
		return parentGroupAction.search(exampleParentGroup);
	}

	public void save(ParentGroup parentGroup) {
		parentGroupAction.persist(parentGroup);
	}

}
