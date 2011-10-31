
	
package com.oreon.smartsis.web.action.domain;
	

import java.util.List;

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Parent;
import com.oreon.smartsis.domain.Student;

	
//@Scope(ScopeType.CONVERSATION)
@Name("parentAction")
public class ParentAction extends ParentActionBase implements java.io.Serializable{
	
	
	public List<Student> getCurrentStudents(){
		Parent parent = getCurrentLoggedInParent();
		if(parent == null) return null;
		String qry = "select s from Student s where s.primary.id = ?1 or s.secondary.id = ?1" ;
		return executeQuery(qry, parent.getId());
	}
}
	