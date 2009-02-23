package org.cerebrum.entities.demogrpahics;

import javax.persistence.Entity;


@Entity
public class Physician extends User {

	private String ohipNumber;

	public void setOhipNumber(String ohipNumber) {
		this.ohipNumber = ohipNumber;
	}

	public String getOhipNumber() {
		return ohipNumber;
	}

}
