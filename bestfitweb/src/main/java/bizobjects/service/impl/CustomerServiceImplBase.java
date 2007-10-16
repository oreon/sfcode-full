package bizobjects.service.impl;

import bizobjects.Customer;
import bizobjects.service.CustomerService;
import bizobjects.dao.CustomerDao;
import java.util.List;
import bizobjects.service.CustomerService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

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

	private AuthorityService authorityService;

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	//// Delegate all crud operations to the Dao ////

	public Customer save(Customer customer) {
		Long id = customer.getId();
		checkUniqueConstraints(customer);
		customerDao.save(customer);

		if (id == null) //creating user for first time, assign authority
			assignDefaultAuthority(customer);

		return customer;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(Customer customer) {
		Customer

		existingCustomer = customerDao.findByUsername(customer.getUserAccount()
				.getUsername());
		ensureUnique(customer, existingCustomer, "Entity.exists.withUsername");

		existingCustomer = customerDao.findByEmail(customer.getPrimaryAddress()
				.getEmail());
		ensureUnique(customer, existingCustomer, "Entity.exists.withEmail");

	}

	private void assignDefaultAuthority(Customer customer) {
		Authority authority = new Authority();
		authority.setUser(customer.getUserAccount());
		authority.setAuthority("role_customer");
		authorityService.save(authority);
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

	public List<Customer> findByLastName(String lastName) {
		return customerDao.findByLastName(lastName);
	}

	public Customer findByUsername(String username) {
		return customerDao.findByUsername(username);
	}

	public Customer findByEmail(String email) {
		return customerDao.findByEmail(email);
	}

	public List<Customer> searchByExample(Customer customer) {
		return customerDao.searchByExample(customer);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
