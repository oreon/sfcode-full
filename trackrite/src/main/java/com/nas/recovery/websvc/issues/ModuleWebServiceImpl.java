package com.nas.recovery.websvc.issues;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.issues.Module;

@WebService(endpointInterface = "com.nas.recovery.websvc.issues.ModuleWebService", serviceName = "ModuleWebService")
@Name("moduleWebService")
public class ModuleWebServiceImpl implements ModuleWebService {

	@In(create = true)
	com.nas.recovery.web.action.issues.ModuleAction moduleAction;

	public Module loadById(Long id) {
		return moduleAction.loadFromId(id);
	}

	public List<Module> findByExample(Module exampleModule) {
		return moduleAction.search(exampleModule);
	}

	public void save(Module module) {
		moduleAction.persist(module);
	}

}
