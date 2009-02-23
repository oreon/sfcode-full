package org.cerebrum.entities.demogrpahics;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Patient extends Person {

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "primaryPhysician_id", nullable = true)
	private Physician primaryPhysician;

	public void setPrimaryPhysician(Physician primaryPhysician) {
		this.primaryPhysician = primaryPhysician;
	}

	public Physician getPrimaryPhysician() {
		return primaryPhysician;
	}

}
