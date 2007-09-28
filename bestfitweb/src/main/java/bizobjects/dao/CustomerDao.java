package bizobjects.dao;

import java.util.List;

import org.witchcraft.model.support.dao.GenericDAO;

import bizobjects.Customer;

public interface CustomerDao extends GenericDAO<Customer>{

	public List<Customer> findByLastName(String lastName);

	public Customer findByUsername(String username);

	public Customer findByEmail(String email);

}
