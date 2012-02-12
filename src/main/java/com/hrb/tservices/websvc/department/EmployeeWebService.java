package com.hrb.tservices.websvc.department;

import javax.jws.WebService;
import com.hrb.tservices.domain.department.Employee;
import java.util.List;

@WebService
public interface EmployeeWebService {

	public Employee loadById(Long id);

	public List<Employee> findByExample(Employee exampleEmployee);

	public void save(Employee employee);

	public String register();

	public String login();

	public com.hrb.tservices.domain.department.Employee findByPhone(String phone);

	public String retrieveCredentials();

}
