package com.oreon.smartsis.websvc.users;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.users.User;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.users.UserWebService", serviceName = "UserWebService")
@Name("userWebService")
public class UserWebServiceImpl implements UserWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.users.UserAction userAction;

	public User loadById(Long id) {
		return userAction.loadFromId(id);
	}

	public List<User> findByExample(User exampleUser) {
		return userAction.search(exampleUser);
	}

	public void save(User user) {
		userAction.persist(user);
	}

	public void enableAccount() {
		userAction.enableAccount();
	}

	public void disableAccount() {
		userAction.disableAccount();
	}

	public String login() {
		return userAction.login();
	}

	public String retrieveCredentials() {
		return userAction.retrieveCredentials();
	}

}
