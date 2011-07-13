package com.oreon.smartsis.websvc.users;

import javax.jws.WebService;
import com.oreon.smartsis.users.User;
import java.util.List;

@WebService
public interface UserWebService {

	public User loadById(Long id);

	public List<User> findByExample(User exampleUser);

	public void save(User user);

	public void enableAccount();

	public void disableAccount();

	public String login();

	public String retrieveCredentials();

}
