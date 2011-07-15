package com.oreon.inventory.websvc.domain;

import javax.jws.WebService;
import com.oreon.inventory.domain.Employee;
import java.util.List;

@WebService
public interface EmployeeWebService {

	public Employee loadById(Long id);

	public List<Employee> findByExample(Employee exampleEmployee);

	public void save(Employee employee);

	public String register();

	public String login();

	public com.oreon.inventory.domain.Employee findByPhone(String phone);

	public String retrieveCredentials();

}
