package bizobjects.dao;

import bizobjects.Customer;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

public interface CustomerDao extends GenericDAO<Customer> {

	public List<Customer> findByLastName(String lastName);

	public Customer findByUsername(String username);

	public Customer findByEmail(String email);

}
