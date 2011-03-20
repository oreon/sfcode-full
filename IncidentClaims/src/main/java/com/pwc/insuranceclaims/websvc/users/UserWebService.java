package com.pwc.insuranceclaims.websvc.users;

import javax.jws.WebService;
import com.pwc.insuranceclaims.users.User;
import java.util.List;

@WebService
public interface UserWebService {

	public User loadById(Long id);

	public List<User> findByExample(User exampleUser);

	public void save(User user);

}
