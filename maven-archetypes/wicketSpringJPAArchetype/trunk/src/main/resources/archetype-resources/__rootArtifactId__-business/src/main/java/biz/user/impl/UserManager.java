package ${package}.biz.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${package}.biz.user.IUserManager;
import ${package}.dao.user.IUserDAO;
import ${package}.domain.user.User;

/**
 * @author Kamalpreet Singh
 *
 */
@Service("userManager")
@Transactional(readOnly = true)
public class UserManager implements IUserManager {

	@Resource(name = "userDAO")
	private IUserDAO userDAO;
	
	@Override
	public boolean login(String userName, String password) {
		if (userName == null || password == null) {
			return false;
		}
		
		// TODO: find User by userName or email.
		// TODO: compare passwords.
		
		return false;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User createUser(User user) {
		return userDAO.create(user);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User updateUser(User user) {
		return userDAO.update(user);
	}
	
	@Override
	public User findUserById(Long userId) {
		return userDAO.findById(userId);
	}
	
	@Override
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}
	
	@Override
	public User findActiveUserByEmail(String email) {
		return userDAO.findActiveUserByEmail(email);
	}
}
