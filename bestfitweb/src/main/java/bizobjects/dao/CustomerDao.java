package bizobjects.dao;

import bizobjects.Customer;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public interface CustomerDao {
    public Customer save(Customer customer);

    public void delete(Customer customer);

    public Customer load(Long id);

    public List<Customer> loadAll();

    public List<Customer> findByfirstName(Object firstName);

    public List<Customer> findBylastName(Object lastName);

    public List<Customer> searchByExample(Customer customer);
}
