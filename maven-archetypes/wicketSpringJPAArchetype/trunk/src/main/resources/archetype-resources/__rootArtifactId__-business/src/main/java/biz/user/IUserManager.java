package ${package}.biz.user;

import ${package}.domain.user.User;

/**
 * @author Kamalpreet Singh
 *
 */
public interface IUserManager {

	boolean login(String userName, String password);
	
	User createUser(User user);
	
	User updateUser(User user);
	
	User findUserById(Long userId);
	
	User findUserByEmail(String email);
	
	User findActiveUserByEmail(String email);
}
