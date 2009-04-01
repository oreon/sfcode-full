package org.cerebrum.domain.encounter;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "encounter")
@Name("encounter")
@Filter(name = "archiveFilterDef")
public class Encounter extends BusinessEntity {

	//complaints->encounter ->Encounter->Complaint->Complaint

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Encounter_ID", nullable = true)
	private Set<Complaint> complaints = new HashSet<Complaint>();

	@Lob
	protected String notes;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	protected org.cerebrum.domain.patient.Patient patient;

	//prescribedTests->encounter ->Encounter->Encounter->Encounter

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Encounter_ID", nullable = true)
	private Set<PrescribedTest> prescribedTests = new HashSet<PrescribedTest>();

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setPatient(org.cerebrum.domain.patient.Patient patient) {
		this.patient = patient;
	}

	public org.cerebrum.domain.patient.Patient getPatient() {
		return patient;
	}

	public void setPrescribedTests(Set<PrescribedTest> prescribedTests) {
		this.prescribedTests = prescribedTests;
	}

	public Set<PrescribedTest> getPrescribedTests() {
		return prescribedTests;
	}

	@Transient
	public String getDisplayName() {
		return complaints + "";
	}

}
