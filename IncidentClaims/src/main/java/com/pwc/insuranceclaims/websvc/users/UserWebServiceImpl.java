package com.pwc.insuranceclaims.websvc.users;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.users.User;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.users.UserWebService", serviceName = "UserWebService")
@Name("userWebService")
public class UserWebServiceImpl implements UserWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.users.UserAction userAction;

	public User loadById(Long id) {
		return userAction.loadFromId(id);
	}

	public List<User> findByExample(User exampleUser) {
		return userAction.search(exampleUser);
	}

	public void save(User user) {
		userAction.persist(user);
	}

}
