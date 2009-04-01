package org.cerebrum.domain.patient;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "immunization")
@Name("immunization")
@Filter(name = "archiveFilterDef")
public class Immunization extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	protected Patient patient;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "immunizationType_id", nullable = false)
	protected ImmunizationType immunizationType;

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setImmunizationType(ImmunizationType immunizationType) {
		this.immunizationType = immunizationType;
	}

	public ImmunizationType getImmunizationType() {
		return immunizationType;
	}

	@Transient
	public String getDisplayName() {
		return patient + "";
	}

}
