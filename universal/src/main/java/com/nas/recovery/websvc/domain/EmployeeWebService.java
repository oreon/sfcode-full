package com.nas.recovery.websvc.domain;

import javax.jws.WebService;
import com.oreon.tapovan.domain.Employee;
import java.util.List;

@WebService
public interface EmployeeWebService {

	public Employee loadById(Long id);

	public List<Employee> findByExample(Employee exampleEmployee);

	public void save(Employee employee);

	/* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	 * support interfaces directly.  Special XmlAdapter classes need to
	 * be written to handle them
	 */
	//String sayHiToUser(Employee user);
}
