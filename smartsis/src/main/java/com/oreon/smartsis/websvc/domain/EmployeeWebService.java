package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Employee;
import java.util.List;

@WebService
public interface EmployeeWebService {

	public Employee loadById(Long id);

	public List<Employee> findByExample(Employee exampleEmployee);

	public void save(Employee employee);

}
