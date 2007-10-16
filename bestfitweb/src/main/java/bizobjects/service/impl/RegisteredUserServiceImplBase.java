package bizobjects.service.impl;

import bizobjects.RegisteredUser;
import bizobjects.service.RegisteredUserService;
import bizobjects.dao.RegisteredUserDao;
import java.util.List;
import bizobjects.service.RegisteredUserService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class RegisteredUserServiceImplBase
		extends
			BaseServiceImpl<RegisteredUser> implements RegisteredUserService {

	private static final Logger log = Logger
			.getLogger(RegisteredUserServiceImplBase.class);

	private RegisteredUserDao registeredUserDao;

	public void setRegisteredUserDao(RegisteredUserDao registeredUserDao) {
		this.registeredUserDao = registeredUserDao;
	}

	@Override
	public GenericDAO<RegisteredUser> getDao() {
		return registeredUserDao;
	}

	//// Delegate all crud operations to the Dao ////

	public RegisteredUser save(RegisteredUser registeredUser) {
		Long id = registeredUser.getId();
		checkUniqueConstraints(registeredUser);
		registeredUserDao.save(registeredUser);

		return registeredUser;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(RegisteredUser registeredUser) {
		RegisteredUser

		existingRegisteredUser = registeredUserDao
				.findByUsername(registeredUser.getUserAccount().getUsername());
		ensureUnique(registeredUser, existingRegisteredUser,
				"Entity.exists.withUsername");

		existingRegisteredUser = registeredUserDao.findByEmail(registeredUser
				.getPrimaryAddress().getEmail());
		ensureUnique(registeredUser, existingRegisteredUser,
				"Entity.exists.withEmail");

	}

	public void delete(RegisteredUser registeredUser) {
		registeredUserDao.delete(registeredUser);
	}

	public RegisteredUser load(Long id) {
		return registeredUserDao.load(id);
	}

	public List<RegisteredUser> loadAll() {
		return registeredUserDao.loadAll();
	}

	public List<RegisteredUser> findByLastName(String lastName) {
		return registeredUserDao.findByLastName(lastName);
	}

	public RegisteredUser findByUsername(String username) {
		return registeredUserDao.findByUsername(username);
	}

	public RegisteredUser findByEmail(String email) {
		return registeredUserDao.findByEmail(email);
	}

	public List<RegisteredUser> searchByExample(RegisteredUser registeredUser) {
		return registeredUserDao.searchByExample(registeredUser);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
