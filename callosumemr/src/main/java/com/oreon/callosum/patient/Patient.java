package com.oreon.callosum.patient;



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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
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
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

import org.witchcraft.utils.*;

@Entity
@Table(name = "patient")
@Filter(name = "archiveFilterDef")
@Name("patient")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Patient extends com.oreon.callosum.patient.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -586507236L;

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Admission> admissions = new HashSet<Admission>();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Prescription> prescriptions = new HashSet<Prescription>();

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "streetAddress", column = @Column(name = "address_streetAddress")),

			@AttributeOverride(name = "city", column = @Column(name = "address_city")),

			@AttributeOverride(name = "State", column = @Column(name = "address_State")),

			@AttributeOverride(name = "phone", column = @Column(name = "address_phone"))

	})
	protected Address address = new Address();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.callosum.unusualoccurences.UnusualOccurence> unusualOccurences = new HashSet<com.oreon.callosum.unusualoccurences.UnusualOccurence>();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Document> documents = new HashSet<Document>();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Allergy> allergys = new HashSet<Allergy>();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Immunization> immunizations = new HashSet<Immunization>();

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String healthNumber;

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
			Set<com.oreon.callosum.unusualoccurences.UnusualOccurence> unusualOccurences) {
		this.unusualOccurences = unusualOccurences;
	}

	public Set<com.oreon.callosum.unusualoccurences.UnusualOccurence> getUnusualOccurences() {
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

	@Transient
	public String getDisplayName() {
		return super.getDisplayName();
	}

	@Transient
	public String getPopupInfo() {
		return age + " " + gender;
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

}
