package org.cerebrum.domain.facility;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "bed")
@Name("bed")
@Filter(name = "archiveFilterDef")
public class Bed extends BusinessEntity {

	protected Integer number;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = true)
	protected org.cerebrum.domain.patient.Patient patient;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id", nullable = false)
	protected Ward ward;

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setPatient(org.cerebrum.domain.patient.Patient patient) {
		this.patient = patient;
	}

	public org.cerebrum.domain.patient.Patient getPatient() {
		return patient;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public Ward getWard() {
		return ward;
	}

	@Transient
	public String getDisplayName() {
		return number + "";
	}

}
