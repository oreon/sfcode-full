package com.pwc.insuranceclaims.websvc.domain;

import javax.jws.WebService;
import com.pwc.insuranceclaims.domain.Employee;
import java.util.List;

@WebService
public interface EmployeeWebService {

	public Employee loadById(Long id);

	public List<Employee> findByExample(Employee exampleEmployee);

	public void save(Employee employee);

	public String register();

	public String login();

	public com.pwc.insuranceclaims.domain.Employee findByPhone(String phone);

	public String retrieveCredentials();

}
