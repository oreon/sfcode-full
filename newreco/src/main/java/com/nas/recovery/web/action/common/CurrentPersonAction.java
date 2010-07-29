package com.nas.recovery.web.action.common;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.nas.recovery.domain.loan.Person;

@Scope(ScopeType.CONVERSATION)
@Name("personAction")
public class CurrentPersonAction {

	private Person person;

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	} 
	
}
