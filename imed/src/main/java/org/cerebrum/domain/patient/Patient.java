package org.cerebrum.domain.patient;

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
@Table(name = "patient")
@Name("patient")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Patient extends org.cerebrum.domain.demographics.Person {

	//allergies->patient ->Patient->Allergy->Allergy

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	@IndexedEmbedded
	private Set<Allergy> allergies = new HashSet<Allergy>();

	//immunizations->patient ->Patient->Immunization->Immunization

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	@IndexedEmbedded
	private Set<Immunization> immunizations = new HashSet<Immunization>();

	protected BloodGroup bloodGroup;

	@Lob
	protected String medicalHistory;

	@Lob
	protected String pastMedications;

	//prescriptions->patient ->Patient->Prescription->Prescription

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	@IndexedEmbedded
	private Set<org.cerebrum.domain.prescription.Prescription> prescriptions = new HashSet<org.cerebrum.domain.prescription.Prescription>();

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "primaryPhysician_id", nullable = true)
	@ContainedIn
	protected org.cerebrum.domain.provider.Physician primaryPhysician;

	//documents->patient ->Patient->Patient->Patient

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	@IndexedEmbedded
	private Set<Document> documents = new HashSet<Document>();

	//encounters->patient ->Patient->Encounter->Encounter

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient_ID", nullable = true)
	@IndexedEmbedded
	private Set<org.cerebrum.domain.encounter.Encounter> encounters = new HashSet<org.cerebrum.domain.encounter.Encounter>();

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

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setEncounters(
			Set<org.cerebrum.domain.encounter.Encounter> encounters) {
		this.encounters = encounters;
	}

	public Set<org.cerebrum.domain.encounter.Encounter> getEncounters() {
		return encounters;
	}

	@Transient
	public String getDisplayName() {
		return super.getDisplayName();
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
