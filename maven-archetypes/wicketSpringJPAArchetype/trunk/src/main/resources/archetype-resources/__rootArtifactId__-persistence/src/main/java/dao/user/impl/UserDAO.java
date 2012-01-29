package ${package}.dao.user.impl;

import org.springframework.stereotype.Repository;

import ${package}.dao.impl.GenericDAO;
import ${package}.dao.user.IUserDAO;
import ${package}.domain.user.User;

/**
 * @author Kamalpreet Singh
 *
 */
@Repository("userDAO")
public class UserDAO extends GenericDAO<User, Long> implements IUserDAO {

	@Override
	public User findUserByEmail(String email) {
		String queryString = "select user from User user where user.email = ?1";
		return executeSingleResultQuery(queryString, email);
	}
	
	@Override
	public User findActiveUserByEmail(String email) {
		String queryString = "select user from User user where user.email = ?1 and user.active = ?2";
		return executeSingleResultQuery(queryString, email, true);
	}
}
