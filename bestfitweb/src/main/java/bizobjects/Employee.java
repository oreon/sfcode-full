package bizobjects;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class Employee extends EmployeeBase {

	private static final Logger log = Logger.getLogger(Employee.class);

	public Employee employeeInstance() {
		return this;
	}
}
