package com.hrb.tservices.websvc.department;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.department.Employee;

@WebService(endpointInterface = "com.hrb.tservices.websvc.department.EmployeeWebService", serviceName = "EmployeeWebService")
@Name("employeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

	@In(create = true)
	com.hrb.tservices.web.action.department.EmployeeAction employeeAction;

	public Employee loadById(Long id) {
		return employeeAction.loadFromId(id);
	}

	public List<Employee> findByExample(Employee exampleEmployee) {
		return employeeAction.search(exampleEmployee);
	}

	public void save(Employee employee) {
		employeeAction.persist(employee);
	}

	public String register() {
		return employeeAction.register();
	}

	public String login() {
		return employeeAction.login();
	}

	public com.hrb.tservices.domain.department.Employee findByPhone(String phone) {
		return employeeAction.findByPhone(phone);
	}

	public String retrieveCredentials() {
		return employeeAction.retrieveCredentials();
	}

}
