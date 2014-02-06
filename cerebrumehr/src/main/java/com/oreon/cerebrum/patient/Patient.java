package com.oreon.cerebrum.patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.witchcraft.base.entity.BaseEntity;

@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Patient extends com.oreon.cerebrum.patient.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -586507236L;

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Admission> admissions = new HashSet<Admission>();

	public void addAdmission(Admission admission) {

		admission.setPatient(this);

		this.admissions.add(admission);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.Admission> getListAdmissions() {
		return new ArrayList<com.oreon.cerebrum.patient.Admission>(admissions);
	}

	//JSF Friendly function to get count of collections
	public int getAdmissionsCount() {
		return admissions.size();
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<com.oreon.cerebrum.prescription.Prescription> prescriptions = new HashSet<com.oreon.cerebrum.prescription.Prescription>();

	public void addPrescription(
			com.oreon.cerebrum.prescription.Prescription prescription) {

		prescription.setPatient(this);

		this.prescriptions.add(prescription);
	}

	@Transient
	public List<com.oreon.cerebrum.prescription.Prescription> getListPrescriptions() {
		return new ArrayList<com.oreon.cerebrum.prescription.Prescription>(
				prescriptions);
	}

	//JSF Friendly function to get count of collections
	public int getPrescriptionsCount() {
		return prescriptions.size();
	}

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "streetAddress", column = @Column(name = "address_streetAddress")),

			@AttributeOverride(name = "city", column = @Column(name = "address_city")),

			@AttributeOverride(name = "State", column = @Column(name = "address_State")),

			@AttributeOverride(name = "phone", column = @Column(name = "address_phone"))

	})
	protected Address address = new Address();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> unusualOccurences = new HashSet<com.oreon.cerebrum.unusualoccurences.UnusualOccurence>();

	public void addUnusualOccurence(
			com.oreon.cerebrum.unusualoccurences.UnusualOccurence unusualOccurence) {

		unusualOccurence.setPatient(this);

		this.unusualOccurences.add(unusualOccurence);
	}

	@Transient
	public List<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> getListUnusualOccurences() {
		return new ArrayList<com.oreon.cerebrum.unusualoccurences.UnusualOccurence>(
				unusualOccurences);
	}

	//JSF Friendly function to get count of collections
	public int getUnusualOccurencesCount() {
		return unusualOccurences.size();
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<PatientDocument> patientDocuments = new HashSet<PatientDocument>();

	public void addPatientDocument(PatientDocument patientDocument) {

		patientDocument.setPatient(this);

		this.patientDocuments.add(patientDocument);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.PatientDocument> getListPatientDocuments() {
		return new ArrayList<com.oreon.cerebrum.patient.PatientDocument>(
				patientDocuments);
	}

	//JSF Friendly function to get count of collections
	public int getPatientDocumentsCount() {
		return patientDocuments.size();
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Allergy> allergys = new HashSet<Allergy>();

	public void addAllergy(Allergy allergy) {

		allergy.setPatient(this);

		this.allergys.add(allergy);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.Allergy> getListAllergys() {
		return new ArrayList<com.oreon.cerebrum.patient.Allergy>(allergys);
	}

	//JSF Friendly function to get count of collections
	public int getAllergysCount() {
		return allergys.size();
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Immunization> immunizations = new HashSet<Immunization>();

	public void addImmunization(Immunization immunization) {

		immunization.setPatient(this);

		this.immunizations.add(immunization);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.Immunization> getListImmunizations() {
		return new ArrayList<com.oreon.cerebrum.patient.Immunization>(
				immunizations);
	}

	//JSF Friendly function to get count of collections
	public int getImmunizationsCount() {
		return immunizations.size();
	}

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String healthNumber

	;

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<VitalValue> vitalValues = new HashSet<VitalValue>();

	public void addVitalValue(VitalValue vitalValue) {

		vitalValue.setPatient(this);

		this.vitalValues.add(vitalValue);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.VitalValue> getListVitalValues() {
		return new ArrayList<com.oreon.cerebrum.patient.VitalValue>(vitalValues);
	}

	//JSF Friendly function to get count of collections
	public int getVitalValuesCount() {
		return vitalValues.size();
	}

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "medicalHistory", column = @Column(name = "history_medicalHistory")),

			@AttributeOverride(name = "socialHistory", column = @Column(name = "history_socialHistory")),

			@AttributeOverride(name = "familyHistory", column = @Column(name = "history_familyHistory")),

			@AttributeOverride(name = "medications", column = @Column(name = "history_medications")),

			@AttributeOverride(name = "allergies", column = @Column(name = "history_allergies"))

	})
	protected History history = new History();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<com.oreon.cerebrum.encounter.Encounter> encounters = new HashSet<com.oreon.cerebrum.encounter.Encounter>();

	public void addEncounter(com.oreon.cerebrum.encounter.Encounter encounter) {

		encounter.setPatient(this);

		this.encounters.add(encounter);
	}

	@Transient
	public List<com.oreon.cerebrum.encounter.Encounter> getListEncounters() {
		return new ArrayList<com.oreon.cerebrum.encounter.Encounter>(encounters);
	}

	//JSF Friendly function to get count of collections
	public int getEncountersCount() {
		return encounters.size();
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<com.oreon.cerebrum.charts.AppliedChart> appliedCharts = new HashSet<com.oreon.cerebrum.charts.AppliedChart>();

	public void addAppliedChart(
			com.oreon.cerebrum.charts.AppliedChart appliedChart) {

		appliedChart.setPatient(this);

		this.appliedCharts.add(appliedChart);
	}

	@Transient
	public List<com.oreon.cerebrum.charts.AppliedChart> getListAppliedCharts() {
		return new ArrayList<com.oreon.cerebrum.charts.AppliedChart>(
				appliedCharts);
	}

	//JSF Friendly function to get count of collections
	public int getAppliedChartsCount() {
		return appliedCharts.size();
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<com.oreon.cerebrum.charts.ChartProcedure> chartProcedures = new HashSet<com.oreon.cerebrum.charts.ChartProcedure>();

	public void addChartProcedure(
			com.oreon.cerebrum.charts.ChartProcedure chartProcedure) {

		chartProcedure.setPatient(this);

		this.chartProcedures.add(chartProcedure);
	}

	@Transient
	public List<com.oreon.cerebrum.charts.ChartProcedure> getListChartProcedures() {
		return new ArrayList<com.oreon.cerebrum.charts.ChartProcedure>(
				chartProcedures);
	}

	//JSF Friendly function to get count of collections
	public int getChartProceduresCount() {
		return chartProcedures.size();
	}

	public void setAdmissions(Set<Admission> admissions) {
		this.admissions = admissions;
	}

	public Set<Admission> getAdmissions() {
		return admissions;
	}

	public void setPrescriptions(
			Set<com.oreon.cerebrum.prescription.Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Set<com.oreon.cerebrum.prescription.Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {

		if (address == null)
			address = new com.oreon.cerebrum.patient.Address();
		return address;

	}

	public void setUnusualOccurences(
			Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> unusualOccurences) {
		this.unusualOccurences = unusualOccurences;
	}

	public Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> getUnusualOccurences() {
		return unusualOccurences;
	}

	public void setPatientDocuments(Set<PatientDocument> patientDocuments) {
		this.patientDocuments = patientDocuments;
	}

	public Set<PatientDocument> getPatientDocuments() {
		return patientDocuments;
	}

	public void setAllergys(Set<Allergy> allergys) {
		this.allergys = allergys;
	}

	public Set<Allergy> getAllergys() {
		return allergys;
	}

	public void setImmunizations(Set<Immunization> immunizations) {
		this.immunizations = immunizations;
	}

	public Set<Immunization> getImmunizations() {
		return immunizations;
	}

	public void setHealthNumber(String healthNumber) {
		this.healthNumber = healthNumber;
	}

	public String getHealthNumber() {

		return healthNumber;

	}

	public void setVitalValues(Set<VitalValue> vitalValues) {
		this.vitalValues = vitalValues;
	}

	public Set<VitalValue> getVitalValues() {
		return vitalValues;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public History getHistory() {

		if (history == null)
			history = new com.oreon.cerebrum.patient.History();
		return history;

	}

	public void setEncounters(
			Set<com.oreon.cerebrum.encounter.Encounter> encounters) {
		this.encounters = encounters;
	}

	public Set<com.oreon.cerebrum.encounter.Encounter> getEncounters() {
		return encounters;
	}

	public void setAppliedCharts(
			Set<com.oreon.cerebrum.charts.AppliedChart> appliedCharts) {
		this.appliedCharts = appliedCharts;
	}

	public Set<com.oreon.cerebrum.charts.AppliedChart> getAppliedCharts() {
		return appliedCharts;
	}

	public void setChartProcedures(
			Set<com.oreon.cerebrum.charts.ChartProcedure> chartProcedures) {
		this.chartProcedures = chartProcedures;
	}

	public Set<com.oreon.cerebrum.charts.ChartProcedure> getChartProcedures() {
		return chartProcedures;
	}

	@Transient
	public String getDisplayName() {
		try {
			return super.getDisplayName();
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getPopupInfo() {
		try {
			return age + " " + gender;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("healthNumber");

		listSearchableFields.add("address.streetAddress");

		listSearchableFields.add("address.city");

		listSearchableFields.add("address.State");

		listSearchableFields.add("address.phone");

		listSearchableFields.add("history.medicalHistory");

		listSearchableFields.add("history.socialHistory");

		listSearchableFields.add("history.familyHistory");

		listSearchableFields.add("history.medications");

		listSearchableFields.add("history.allergies");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getHealthNumber() + " ");

		for (BaseEntity e : admissions) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : prescriptions) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : unusualOccurences) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : patientDocuments) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : allergys) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : immunizations) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : vitalValues) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : encounters) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : appliedCharts) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : chartProcedures) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
