
/**
 * This is generated code - to edit code or override methods use - Employee class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.dao.impl;

import com.oreon.jshoppingcart.domain.Employee;
import com.oreon.jshoppingcart.dao.EmployeeDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class EmployeeDaoImplBase extends BaseDao<Employee>
		implements
			EmployeeDao {

	//// FINDERS ///// 

	@SuppressWarnings("unchecked")
	public/**
	 * Since email is unique, will try to return a single Employee by the
	 * email - if no record is found null will be returned
	 * 
	 */
	Employee findByEmail(String email) {

		String qryString = "select c from Employee c where c.email = ?1";

		Query query = entityManager.createQuery(qryString).setParameter(1,
				email);
		try {
			return (Employee) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

}
