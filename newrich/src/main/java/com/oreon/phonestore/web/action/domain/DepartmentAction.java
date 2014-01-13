package com.oreon.phonestore.web.action.domain;

import org.jboss.seam.annotations.Name;

//@Scope(ScopeType.CONVERSATION)
@Name("departmentAction")
public class DepartmentAction extends DepartmentActionBase
		implements
			java.io.Serializable {

	private int counter = 0;
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void incCounter(){
		counter++;
		System.out.println(counter);
	}
}
