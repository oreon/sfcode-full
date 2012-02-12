package com.hrb.tservices.websvc.users;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.users.AppRole;

@WebService(endpointInterface = "com.hrb.tservices.websvc.users.AppRoleWebService", serviceName = "AppRoleWebService")
@Name("appRoleWebService")
public class AppRoleWebServiceImpl implements AppRoleWebService {

	@In(create = true)
	com.hrb.tservices.web.action.users.AppRoleAction appRoleAction;

	public AppRole loadById(Long id) {
		return appRoleAction.loadFromId(id);
	}

	public List<AppRole> findByExample(AppRole exampleAppRole) {
		return appRoleAction.search(exampleAppRole);
	}

	public void save(AppRole appRole) {
		appRoleAction.persist(appRole);
	}

}
