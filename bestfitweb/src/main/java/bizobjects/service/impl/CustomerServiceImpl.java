package bizobjects.service.impl;

import bizobjects.Customer;

import bizobjects.dao.CustomerDao;

import bizobjects.service.CustomerService;
import bizobjects.service.CustomerService;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    //// Delegate all crud operations to the Dao ////
    public Customer save(Customer customer) {
        return customerDao.save(customer);
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

    public List<Customer> findByfirstName(Object firstName) {
        return customerDao.findByfirstName(firstName);
    }

    public List<Customer> findBylastName(Object lastName) {
        return customerDao.findBylastName(lastName);
    }

    public List<Customer> searchByExample(Customer customer) {
        return customerDao.searchByExample(customer);
    }

    /*
    public List query(String queryString, Object... params) {
            return basicDAO.query(queryString, params);
    }*/
}
