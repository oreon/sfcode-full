package bizobjects.dao.impl;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao {

	// // FINDERS /////

	@SuppressWarnings("unchecked")
	public List<Customer> findByLastName(String lastName) {
		Query query = entityManager.createQuery(
				"select c from Customer c where c.lastName = ?1").setParameter(
				1, lastName);

		return query.getResultList();
	}

	/**
	 * Since username is unique, will try to return a single customer by the
	 * username - if no customer is found null will be returned
	 * 
	 * @see bizobjects.dao.CustomerDao#findByUsername(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Customer findByUsername(String username) {
		String qryString = "select c from Customer c where c.userAccount.username = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				username);
		try {
			return (Customer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Customer findByEmail(String email) {
		Query query = entityManager.createQuery(
				"select c from Customer c where c.primaryAddress.email = ?1")
				.setParameter(1, email);
		try {
			return (Customer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
