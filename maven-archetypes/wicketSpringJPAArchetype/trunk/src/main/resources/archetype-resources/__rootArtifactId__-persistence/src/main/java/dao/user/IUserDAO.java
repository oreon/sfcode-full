package ${package}.dao.user;

import ${package}.dao.IGenericDAO;
import ${package}.domain.user.User;

/**
 * @author Kamalpreet Singh
 * 
 */
public interface IUserDAO extends IGenericDAO<User, Long> {

	User findUserByEmail(String email);
	
	User findActiveUserByEmail(String email);
}
