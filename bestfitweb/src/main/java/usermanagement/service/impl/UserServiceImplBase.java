package usermanagement.service.impl;

import usermanagement.User;
import usermanagement.service.UserService;
import usermanagement.dao.UserDao;
import java.util.List;
import usermanagement.service.UserService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.errorhandling.BusinessException;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UserServiceImplBase implements UserService {

	private static final Logger log = Logger
			.getLogger(UserServiceImplBase.class);

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//// Delegate all crud operations to the Dao ////

	public User save(User user) {
		checkUniqueConstraints(user);
		userDao.save(user);

		return user;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(User user) {
		User

		existingUser = userDao.findByUsername(user.getUsername());
		ensureUnique(user, existingUser, "Entity.exists.withUsername");

	}

	private void ensureUnique(User user, User existingUser, String exceptionId) {
		if (existingUser == null)
			return; //no customer exists with the given email - no need to check unique constraint violation

		if (user.getId() == null) { // for a new entity
			throw new BusinessException(exceptionId);
		} else {//for updating an existing entiy
			if (existingUser.getId().longValue() != user.getId().longValue())
				throw new BusinessException(exceptionId);
		}

	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public User load(Long id) {
		return userDao.load(id);
	}

	public List<User> loadAll() {
		return userDao.loadAll();
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public List<User> searchByExample(User user) {
		return userDao.searchByExample(user);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
