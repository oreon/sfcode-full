package com.nas.recovery.websvc.domain;
import javax.jws.WebService;
import java.util.List;

import com.oreon.tapovan.domain.Employee;

@WebService(endpointInterface = "com.nas.recovery.websvc.domain.EmployeeWebService", serviceName = "EmployeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

	public Employee loadById(Long id) {

		return null;
	}

	public List<Employee> findByExample(Employee exampleEmployee) {
		return null;
	}

	public void save(Employee employee) {
	}
}
