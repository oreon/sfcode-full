package bizobjects.service.impl;

import bizobjects.Customer;
import bizobjects.service.CustomerService;
import bizobjects.dao.CustomerDao;
import java.util.List;
import bizobjects.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	private AuthorityService authorityService;

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public Customer save(Customer customer) {
		checkUniqueConstraints(customer);
		customerDao.save(customer);
		assignDefaultAuthority(customer);
		
		return customer;
	}

	private void assignDefaultAuthority(Customer customer) {
		Authority authority = new Authority();
		authority.setUser(customer.getUserAccount());
		authority.setAuthority("role_customer");
		authorityService.save(authority);
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

	private void ensureUnique(Customer customer, Customer existingCustomer,
			String exceptionId) {
		if (existingCustomer == null)
			return; //no customer exists with the given email - no need to check unique constraint violation

		if (customer.getId() == null) { // for a new entity
			throw new RuntimeException(exceptionId);
		} else {//for updating an existing entiy
			if (existingCustomer.getId() != customer.getId())
				throw new RuntimeException(exceptionId);
		}

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
