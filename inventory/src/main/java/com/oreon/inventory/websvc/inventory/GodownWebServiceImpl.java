package com.oreon.inventory.websvc.inventory;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.inventory.inventory.Godown;

@WebService(endpointInterface = "com.oreon.inventory.websvc.inventory.GodownWebService", serviceName = "GodownWebService")
@Name("godownWebService")
public class GodownWebServiceImpl implements GodownWebService {

	@In(create = true)
	com.oreon.inventory.web.action.inventory.GodownAction godownAction;

	public Godown loadById(Long id) {
		return godownAction.loadFromId(id);
	}

	public List<Godown> findByExample(Godown exampleGodown) {
		return godownAction.search(exampleGodown);
	}

	public void save(Godown godown) {
		godownAction.persist(godown);
	}

}
