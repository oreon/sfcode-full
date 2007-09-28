package bizobjects.dao;

import bizobjects.Employee;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

public interface EmployeeDao extends GenericDAO<Employee> {

	public List<Employee> findByLastName(String lastName);

	public Employee findByCode(int code);

	public Employee findByUsername(String username);

	public Employee findByEmail(String email);

}
