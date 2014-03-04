package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.cerebrum.employee.Employee;

@Name("employeeList")
@Scope(ScopeType.CONVERSATION)
public class EmployeeListQuery extends EmployeeListQueryBase<Employee>
		implements
			java.io.Serializable {

}
