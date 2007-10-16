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

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UserServiceImplBase extends BaseServiceImpl<User>
		implements
			UserService {

	private static final Logger log = Logger
			.getLogger(UserServiceImplBase.class);

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public GenericDAO<User> getDao() {
		return userDao;
	}

	//// Delegate all crud operations to the Dao ////

	public User save(User user) {
		Long id = user.getId();
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
