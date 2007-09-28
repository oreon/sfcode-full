package bizobjects.service.impl;

import bizobjects.Employee;
import bizobjects.service.EmployeeService;
import bizobjects.dao.EmployeeDao;
import java.util.List;
import bizobjects.service.EmployeeService;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	protected static final Logger log = Logger
			.getLogger(EmployeeServiceImpl.class);

	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Employee save(Employee employee) {
		checkUniqueConstraints(employee);
		return employeeDao.save(employee);
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(Employee employee) {
		Employee

		existingEmployee = employeeDao.findByCode(employee.getCode());
		ensureUnique(employee, existingEmployee, "Entity.exists.withCode");

		existingEmployee = employeeDao.findByUsername(employee.getUserAccount()
				.getUsername());
		ensureUnique(employee, existingEmployee, "Entity.exists.withUsername");

		existingEmployee = employeeDao.findByEmail(employee.getPrimaryAddress()
				.getEmail());
		ensureUnique(employee, existingEmployee, "Entity.exists.withEmail");

	}

	private void ensureUnique(Employee employee, Employee existingEmployee,
			String exceptionId) {
		if (existingEmployee == null)
			return; //no customer exists with the given email - no need to check unique constraint violation

		if (employee.getId() == null) { // for a new entity
			throw new RuntimeException(exceptionId);
		} else {//for updating an existing entiy
			if (existingEmployee.getId() != employee.getId())
				throw new RuntimeException(exceptionId);
		}

	}

	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

	public Employee load(Long id) {
		return employeeDao.load(id);
	}

	public List<Employee> loadAll() {
		return employeeDao.loadAll();
	}

	public List<Employee> findByLastName(String lastName) {
		return employeeDao.findByLastName(lastName);
	}

	public Employee findByCode(int code) {
		return employeeDao.findByCode(code);
	}

	public Employee findByUsername(String username) {
		return employeeDao.findByUsername(username);
	}

	public Employee findByEmail(String email) {
		return employeeDao.findByEmail(email);
	}

	public List<Employee> searchByExample(Employee employee) {
		return employeeDao.searchByExample(employee);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
