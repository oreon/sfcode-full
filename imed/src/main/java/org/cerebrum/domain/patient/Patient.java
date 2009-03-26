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
@Table(name = "patient")
@Name("patient")
@Filter(name = "archiveFilterDef")
public class Patient extends org.cerebrum.domain.demographics.Person {

	//allergies->patient ->Patient->Patient->Patient

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	private Set<Allergy> allergies = new HashSet<Allergy>();

	//immunizations->patient ->Patient->Patient->Patient

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	private Set<Immunization> immunizations = new HashSet<Immunization>();

	protected BloodGroup bloodGroup;

	@Lob
	protected String medicalHistory;

	@Lob
	protected String pastMedications;

	//prescriptions->patient ->Patient->Patient->Patient

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	private Set<org.cerebrum.domain.prescription.Prescription> prescriptions = new HashSet<org.cerebrum.domain.prescription.Prescription>();

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "primaryPhysician_id", nullable = true)
	protected org.cerebrum.domain.provider.Physician primaryPhysician;

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

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
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

	public void setPrescriptions(
			Set<org.cerebrum.domain.prescription.Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Set<org.cerebrum.domain.prescription.Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrimaryPhysician(
			org.cerebrum.domain.provider.Physician primaryPhysician) {
		this.primaryPhysician = primaryPhysician;
	}

	public org.cerebrum.domain.provider.Physician getPrimaryPhysician() {
		return primaryPhysician;
	}

	@Transient
	public String getDisplayName() {
		return super.getDisplayName();
	}

}
