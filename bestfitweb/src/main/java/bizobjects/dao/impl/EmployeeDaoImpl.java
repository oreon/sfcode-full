package bizobjects.dao.impl;

import bizobjects.Employee;
import bizobjects.dao.EmployeeDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class EmployeeDaoImpl extends BaseDao<Employee> implements EmployeeDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public List<Employee> findByLastName(String lastName) {
		String qryString = "select c from Employee c where c.lastName = ?1";
		Query query = entityManager.createQuery(qryString).setParameter(1,
				lastName);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public/**
	 * Since code is unique, will try to return a single Employee by the
	 * code - if no record is found null will be returned
	 * 
	 */
	Employee findByCode(int code) {

		String qryString = "select c from Employee c where c.code = ?1";

		Query query = entityManager.createQuery(qryString)
				.setParameter(1, code);
		try {
			return (Employee) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public/**
	 * Since username is unique, will try to return a single Employee by the
	 * username - if no record is found null will be returned
	 * 
	 */
	Employee findByUsername(String username) {

		String qryString = "select c from Employee c where c.userAccount.username = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				username);
		try {
			return (Employee) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public/**
	 * Since email is unique, will try to return a single Employee by the
	 * email - if no record is found null will be returned
	 * 
	 */
	Employee findByEmail(String email) {

		String qryString = "select c from Employee c where c.primaryAddress.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (Employee) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

}
