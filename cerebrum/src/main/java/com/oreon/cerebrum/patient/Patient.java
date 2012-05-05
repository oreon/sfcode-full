package com.oreon.cerebrum.patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.annotations.Filter;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "patient")
@Filter(name = "archiveFilterDef")
@Name("patient")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Patient extends com.oreon.cerebrum.patient.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -586507236L;

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
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
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Prescription> prescriptions = new HashSet<Prescription>();

	public void addPrescription(Prescription prescription) {
		prescription.setPatient(this);
		this.prescriptions.add(prescription);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.Prescription> getListPrescriptions() {
		return new ArrayList<com.oreon.cerebrum.patient.Prescription>(
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
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
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
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Document> documents = new HashSet<Document>();

	public void addDocument(Document document) {
		document.setPatient(this);
		this.documents.add(document);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.Document> getListDocuments() {
		return new ArrayList<com.oreon.cerebrum.patient.Document>(documents);
	}

	//JSF Friendly function to get count of collections
	public int getDocumentsCount() {
		return documents.size();
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
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
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
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

	@NotNull
	@Column(name = "healthNumber", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String healthNumber

	;

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
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

	public void setAdmissions(Set<Admission> admissions) {
		this.admissions = admissions;
	}

	public Set<Admission> getAdmissions() {
		return admissions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {

		return address;

	}

	public void setUnusualOccurences(
			Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> unusualOccurences) {
		this.unusualOccurences = unusualOccurences;
	}

	public Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> getUnusualOccurences() {
		return unusualOccurences;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public Set<Document> getDocuments() {
		return documents;
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
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
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

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getHealthNumber() + " ");

		for (BusinessEntity e : admissions) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : prescriptions) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : unusualOccurences) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : documents) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : allergys) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : immunizations) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : vitalValues) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
