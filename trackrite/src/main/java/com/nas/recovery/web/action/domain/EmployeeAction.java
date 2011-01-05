package com.nas.recovery.web.action.domain;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.ManagedJbpmContext;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.wc.trackrite.users.Role;

import com.nas.recovery.web.action.users.RoleAction;
import com.nas.recovery.web.action.workflowmgt.Play;

//// CMTD @Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase implements
		java.io.Serializable {
	
	@In(create=true)
	RoleAction roleAction;
	
	//@In(create=true)
	//ExecutionContext executionContext;
	
	@In(create=true, value="playProcessAction") 
	Play play;
	
	@Override
	public String save() {
		boolean isNew = getInstance().getId() == null;
		if (isNew) {
			Role role = createRole();
			instance.getUser().getRoles().add(role);
		}
		String result = super.save();
		getInstance().setCreatedByUser(getInstance().getUser());
		super.save();
		if (isNew){
			//personAction.setPerson(person);
			//sendMail("/mails/registrationSuccess.xhtml");
		}
		return result;
	}

	private Role createRole() {
		Role role = roleAction.findByUnqName(definedRole());
		if(role == null) {
			role = new Role();
			role.setName(definedRole());
			roleAction.persist(role);
		}
		return role;
	}

	private String definedRole() {
		// TODO Auto-generated method stub
		return "developer";
	}
	
	public String updateDueDate(TaskInstance task){
	//	TaskInstance task = executionContext.getTaskInstance();
		//ManagedJbpmContext.instance().getGraphSession();
		//task = ExecutionContext.currentExecutionContext().getTaskInstance();
		//task.setDueDate(new java.util.Date());
		return "";
	}

}
