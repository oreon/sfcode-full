package bizobjects.dao.impl;

import bizobjects.RegisteredUser;
import bizobjects.dao.RegisteredUserDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class RegisteredUserDaoImplBase extends BaseDao<RegisteredUser>
		implements
			RegisteredUserDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public List<RegisteredUser> findByLastName(String lastName) {
		String qryString = "select c from RegisteredUser c where c.lastName = ?1";
		Query query = entityManager.createQuery(qryString).setParameter(1,
				lastName);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public/**
	 * Since username is unique, will try to return a single RegisteredUser by the
	 * username - if no record is found null will be returned
	 * 
	 */
	RegisteredUser findByUsername(String username) {

		String qryString = "select c from RegisteredUser c where c.userAccount.username = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				username);
		try {
			return (RegisteredUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public/**
	 * Since email is unique, will try to return a single RegisteredUser by the
	 * email - if no record is found null will be returned
	 * 
	 */
	RegisteredUser findByEmail(String email) {

		String qryString = "select c from RegisteredUser c where c.primaryAddress.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (RegisteredUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

}
