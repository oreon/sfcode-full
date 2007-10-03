package usermanagement.dao.impl;

import usermanagement.User;
import usermanagement.dao.UserDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public/**
	 * Since username is unique, will try to return a single User by the
	 * username - if no record is found null will be returned
	 * 
	 */
	User findByUsername(String username) {

		String qryString = "select c from User c where c.username = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				username);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

}
