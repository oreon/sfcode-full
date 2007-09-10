package bizobjects.dao;

import java.util.List;

import bizobjects.Customer;

public interface ICustomerDao {

	public abstract Customer save(Customer customer);

	public abstract void delete(Customer customer);

	public abstract Customer load(Long id);

	public abstract List<Customer> loadAll();

	public abstract List<Customer> findByfirstName(String firstName);

	public abstract void findBylastName();

}