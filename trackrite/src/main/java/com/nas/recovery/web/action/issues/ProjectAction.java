package com.nas.recovery.web.action.issues;

import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.wc.trackrite.domain.Employee;
import org.wc.trackrite.issues.Issue;

//// CMTD @Scope(ScopeType.CONVERSATION)
@Name("projectAction")
public class ProjectAction extends ProjectActionBase implements
		java.io.Serializable {
	
	@In(scope = ScopeType.BUSINESS_PROCESS, required = false)
	Issue token;
	
	@In(create=true)
	IssueAction issueAction;
	
	
	public String getEmployeeUsernames(){
		token = issueAction.refreshToken(token);
		Set<Employee> emps = token.getProject().getEmployees();
		
		StringBuilder sb = new StringBuilder();
		
		for (Employee employee : emps) {
			sb.append(employee.getUser().getEmail() + ";");
		}
		return sb.toString();
		
	}

}
