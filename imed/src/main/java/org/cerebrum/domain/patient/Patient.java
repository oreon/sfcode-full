package org.cerebrum.domain.patient;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "patient")
@Name("patient")
public class Patient extends org.cerebrum.domain.demographics.Person {

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	private Set<Allergy> allergies = new HashSet<Allergy>();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	private Set<Immunization> immunizations = new HashSet<Immunization>();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	private Set<org.cerebrum.domain.vitals.Vitals> vitalses = new HashSet<org.cerebrum.domain.vitals.Vitals>();

	@Lob
	protected String medicalHistory;

	@Lob
	protected String pastMedications;

	protected BloodGroup bloodGroup;

	public void setAllergies(Set<Allergy> allergies) {
		this.allergies = allergies;
	}

	public Set<Allergy> getAllergies() {
		return allergies;
	}

	public void setImmunizations(Set<Immunization> immunizations) {
		this.immunizations = immunizations;
	}

	public Set<Immunization> getImmunizations() {
		return immunizations;
	}

	public void setVitalses(Set<org.cerebrum.domain.vitals.Vitals> vitalses) {
		this.vitalses = vitalses;
	}

	public Set<org.cerebrum.domain.vitals.Vitals> getVitalses() {
		return vitalses;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setPastMedications(String pastMedications) {
		this.pastMedications = pastMedications;
	}

	public String getPastMedications() {
		return pastMedications;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	@Transient
	public String getDisplayName() {
		return allergies + "";
	}

}
