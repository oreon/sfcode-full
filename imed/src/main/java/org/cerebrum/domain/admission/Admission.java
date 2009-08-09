package org.cerebrum.domain.admission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "admission")
@Name("admission")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Admission extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false, updatable = false)
	@ContainedIn
	protected org.cerebrum.domain.patient.Patient patient;

	//physicians-> ->->Admission->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Admission_ID", nullable = true)
	@IndexedEmbedded
	private Set<org.cerebrum.domain.provider.Physician> physicians = new HashSet<org.cerebrum.domain.provider.Physician>();

	//nurses-> ->->Admission->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Admission_ID", nullable = true)
	@IndexedEmbedded
	private Set<org.cerebrum.domain.provider.Nurse> nurses = new HashSet<org.cerebrum.domain.provider.Nurse>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription_id", nullable = false, updatable = true)
	@ContainedIn
	protected org.cerebrum.domain.prescription.Prescription prescription;

	@Lob
	protected String notes;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "bedAllocation_id", nullable = false, updatable = true)
	@ContainedIn
	protected BedAllocation bedAllocation;

	//complaints->admission ->Admission->Admission->Admission

	@OneToMany(mappedBy = "admission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Admission_ID", nullable = true)
	@IndexedEmbedded
	private Set<DiseaseIncidence> complaints = new HashSet<DiseaseIncidence>();

	public void setPatient(org.cerebrum.domain.patient.Patient patient) {
		this.patient = patient;
	}

	public org.cerebrum.domain.patient.Patient getPatient() {
		return patient;
	}

	public void setPhysicians(
			Set<org.cerebrum.domain.provider.Physician> physicians) {
		this.physicians = physicians;
	}

	public Set<org.cerebrum.domain.provider.Physician> getPhysicians() {
		return physicians;
	}

	public void setNurses(Set<org.cerebrum.domain.provider.Nurse> nurses) {
		this.nurses = nurses;
	}

	public Set<org.cerebrum.domain.provider.Nurse> getNurses() {
		return nurses;
	}

	public void setPrescription(
			org.cerebrum.domain.prescription.Prescription prescription) {
		this.prescription = prescription;
	}

	public org.cerebrum.domain.prescription.Prescription getPrescription() {
		return prescription;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setBedAllocation(BedAllocation bedAllocation) {
		this.bedAllocation = bedAllocation;
	}

	public BedAllocation getBedAllocation() {
		return bedAllocation;
	}

	public void setComplaints(Set<DiseaseIncidence> complaints) {
		this.complaints = complaints;
	}

	public Set<DiseaseIncidence> getComplaints() {
		return complaints;
	}

	@Transient
	public String getDisplayName() {
		return patient + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		return listSearchableFields;
	}

}
