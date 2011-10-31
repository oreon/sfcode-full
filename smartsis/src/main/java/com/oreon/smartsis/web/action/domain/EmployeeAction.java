
	
package com.oreon.smartsis.web.action.domain;
	

import java.util.List;

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Employee;

	
//@Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase implements java.io.Serializable{
	
	public List<Employee> getTeacherEmployees(){
		String qry = " Select e from Employee e where e.employeeType = EmployeeType.Teacher ";
		return executeQuery(qry);
	}
	
	
}
	