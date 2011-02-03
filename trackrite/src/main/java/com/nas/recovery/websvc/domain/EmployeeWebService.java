package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import org.wc.trackrite.domain.Employee;
import java.util.List;

@WebService
public interface EmployeeWebService {

	public Employee loadById(Long id);

	public List<Employee> findByExample(Employee exampleEmployee);

	public void save(Employee employee);

}
