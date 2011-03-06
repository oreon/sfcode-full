package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Employee;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.EmployeeWebService", serviceName = "EmployeeWebService")
@Name("employeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.EmployeeAction employeeAction;

	public Employee loadById(Long id) {
		return employeeAction.loadFromId(id);
	}

	public List<Employee> findByExample(Employee exampleEmployee) {
		return employeeAction.search(exampleEmployee);
	}

	public void save(Employee employee) {
		employeeAction.persist(employee);
	}

}
