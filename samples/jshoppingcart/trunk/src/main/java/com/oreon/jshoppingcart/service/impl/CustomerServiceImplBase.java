
/**
 * This is generated code - to edit code or override methods use - Customer class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.service.impl;

import com.oreon.jshoppingcart.domain.Customer;
import com.oreon.jshoppingcart.service.CustomerService;
import com.oreon.jshoppingcart.dao.CustomerDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CustomerServiceImplBase extends BaseServiceImpl<Customer>
		implements
			CustomerService {

	private static final Logger log = Logger
			.getLogger(CustomerServiceImplBase.class);

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public GenericDAO<Customer> getDao() {
		return customerDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Customer save(Customer customer) {
		Long id = customer.getId();

		customerDao.save(customer);

		return customer;
	}

	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	public Customer load(Long id) {
		return customerDao.load(id);
	}

	public List<Customer> loadAll() {
		return customerDao.loadAll();
	}

	public List<Customer> searchByExample(Customer customer) {
		return customerDao.searchByExample(customer);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
