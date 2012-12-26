package com.oreon.cerebrum.encounter;

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
import org.hibernate.annotations.Filters;
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

import org.witchcraft.model.support.audit.Auditable;

import org.witchcraft.utils.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "encounter")
@Filters({@Filter(name = "archiveFilterDef"),

})
@Name("encounter")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Encounter extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1171400456L;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "physician_id", nullable = true, updatable = true)
	protected com.oreon.cerebrum.employee.Physician physician

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String chiefComplaint

	;

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "encounter_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<PrescribedTest> prescribedTests = new HashSet<PrescribedTest>();

	public void addPrescribedTest(PrescribedTest prescribedTest) {
		prescribedTest.setEncounter(this);
		this.prescribedTests.add(prescribedTest);
	}

	@Transient
	public List<com.oreon.cerebrum.encounter.PrescribedTest> getListPrescribedTests() {
		return new ArrayList<com.oreon.cerebrum.encounter.PrescribedTest>(
				prescribedTests);
	}

	//JSF Friendly function to get count of collections
	public int getPrescribedTestsCount() {
		return prescribedTests.size();
	}

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String progressNotes

	;

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "SysBP", column = @Column(name = "vitals_SysBP")),

			@AttributeOverride(name = "DiasBP", column = @Column(name = "vitals_DiasBP")),

			@AttributeOverride(name = "Temperature", column = @Column(name = "vitals_Temperature")),

			@AttributeOverride(name = "Pulse", column = @Column(name = "vitals_Pulse")),

			@AttributeOverride(name = "RespirationRate", column = @Column(name = "vitals_RespirationRate"))

	})
	protected Vitals vitals = new Vitals();

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "prescription_id", nullable = true, updatable = true)
	protected com.oreon.cerebrum.prescription.Prescription prescription

	;

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "encounter_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Differential> differentials = new HashSet<Differential>();

	public void addDifferential(Differential differential) {
		differential.setEncounter(this);
		this.differentials.add(differential);
	}

	@Transient
	public List<com.oreon.cerebrum.encounter.Differential> getListDifferentials() {
		return new ArrayList<com.oreon.cerebrum.encounter.Differential>(
				differentials);
	}

	//JSF Friendly function to get count of collections
	public int getDifferentialsCount() {
		return differentials.size();
	}

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String physicalExamFindings

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.patient.Patient patient

	;

	public void setPhysician(com.oreon.cerebrum.employee.Physician physician) {
		this.physician = physician;
	}

	public com.oreon.cerebrum.employee.Physician getPhysician() {

		return physician;

	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public String getChiefComplaint() {

		return chiefComplaint;

	}

	public void setPrescribedTests(Set<PrescribedTest> prescribedTests) {
		this.prescribedTests = prescribedTests;
	}

	public Set<PrescribedTest> getPrescribedTests() {
		return prescribedTests;
	}

	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}

	public String getProgressNotes() {

		return progressNotes;

	}

	public void setVitals(Vitals vitals) {
		this.vitals = vitals;
	}

	public Vitals getVitals() {

		return vitals;

	}

	public void setPrescription(
			com.oreon.cerebrum.prescription.Prescription prescription) {
		this.prescription = prescription;
	}

	public com.oreon.cerebrum.prescription.Prescription getPrescription() {

		return prescription;

	}

	public void setDifferentials(Set<Differential> differentials) {
		this.differentials = differentials;
	}

	public Set<Differential> getDifferentials() {
		return differentials;
	}

	public void setPhysicalExamFindings(String physicalExamFindings) {
		this.physicalExamFindings = physicalExamFindings;
	}

	public String getPhysicalExamFindings() {

		return physicalExamFindings;

	}

	public void setPatient(com.oreon.cerebrum.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.cerebrum.patient.Patient getPatient() {

		return patient;

	}

	@Transient
	public String getDisplayName() {
		try {
			return physician + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getChiefComplaintAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(chiefComplaint
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return chiefComplaint != null ? chiefComplaint : "";
		}
	}

	@Transient
	public String getProgressNotesAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(progressNotes
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return progressNotes != null ? progressNotes : "";
		}
	}

	@Transient
	public String getPhysicalExamFindingsAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					physicalExamFindings.trim(), 100, 200, "...");
		} catch (Exception e) {
			return physicalExamFindings != null ? physicalExamFindings : "";
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

		listSearchableFields.add("chiefComplaint");

		listSearchableFields.add("progressNotes");

		listSearchableFields.add("physicalExamFindings");

		listSearchableFields.add("prescribedTests.remarks");

		listSearchableFields.add("differentials.remarks");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getChiefComplaint() + " ");

		builder.append(getProgressNotes() + " ");

		builder.append(getPhysicalExamFindings() + " ");

		if (getPhysician() != null)
			builder
					.append("physician:" + getPhysician().getDisplayName()
							+ " ");

		if (getPrescription() != null)
			builder.append("prescription:" + getPrescription().getDisplayName()
					+ " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		for (BaseEntity e : prescribedTests) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : differentials) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
