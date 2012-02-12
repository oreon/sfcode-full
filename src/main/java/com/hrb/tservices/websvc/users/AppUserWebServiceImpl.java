package com.hrb.tservices.websvc.users;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.users.AppUser;

@WebService(endpointInterface = "com.hrb.tservices.websvc.users.AppUserWebService", serviceName = "AppUserWebService")
@Name("appUserWebService")
public class AppUserWebServiceImpl implements AppUserWebService {

	@In(create = true)
	com.hrb.tservices.web.action.users.AppUserAction appUserAction;

	public AppUser loadById(Long id) {
		return appUserAction.loadFromId(id);
	}

	public List<AppUser> findByExample(AppUser exampleAppUser) {
		return appUserAction.search(exampleAppUser);
	}

	public void save(AppUser appUser) {
		appUserAction.persist(appUser);
	}

}
