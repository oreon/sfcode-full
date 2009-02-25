package org.cerebrum.entities.demogrpahics;

import javax.persistence.Entity;

import org.jboss.seam.annotations.Name;


@Entity
@Name("physician")
public class Physician extends User {

	private String ohipNumber;

	public void setOhipNumber(String ohipNumber) {
		this.ohipNumber = ohipNumber;
	}

	public String getOhipNumber() {
		return ohipNumber;
	}

}
