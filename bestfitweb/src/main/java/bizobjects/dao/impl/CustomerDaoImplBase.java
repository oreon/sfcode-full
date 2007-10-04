package bizobjects.dao.impl;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CustomerDaoImplBase extends BaseDao<Customer>
		implements
			CustomerDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public List<Customer> findByLastName(String lastName) {
		String qryString = "select c from Customer c where c.lastName = ?1";
		Query query = entityManager.createQuery(qryString).setParameter(1,
				lastName);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public/**
	 * Since username is unique, will try to return a single Customer by the
	 * username - if no record is found null will be returned
	 * 
	 */
	Customer findByUsername(String username) {

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
	public/**
	 * Since email is unique, will try to return a single Customer by the
	 * email - if no record is found null will be returned
	 * 
	 */
	Customer findByEmail(String email) {

		String qryString = "select c from Customer c where c.primaryAddress.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (Customer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

}
