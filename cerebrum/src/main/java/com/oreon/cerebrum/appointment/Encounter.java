package com.oreon.cerebrum.appointment;

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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "encounter")
@Filter(name = "archiveFilterDef")
@Name("encounter")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Encounter extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -2016546332L;

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "encounter_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<History> historys = new HashSet<History>();

	public void addHistory(History history) {
		history.setEncounter(this);
		this.historys.add(history);
	}

	@Transient
	public List<com.oreon.cerebrum.appointment.History> getListHistorys() {
		return new ArrayList<com.oreon.cerebrum.appointment.History>(historys);
	}

	//JSF Friendly function to get count of collections
	public int getHistorysCount() {
		return historys.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.cerebrum.patient.Patient patient

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "physician_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.cerebrum.employee.Physician physician

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String notes

	;

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "encounter_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<PrescribedTest> prescribedTests = new HashSet<PrescribedTest>();

	public void addPrescribedTest(PrescribedTest prescribedTest) {
		prescribedTest.setEncounter(this);
		this.prescribedTests.add(prescribedTest);
	}

	@Transient
	public List<com.oreon.cerebrum.appointment.PrescribedTest> getListPrescribedTests() {
		return new ArrayList<com.oreon.cerebrum.appointment.PrescribedTest>(
				prescribedTests);
	}

	//JSF Friendly function to get count of collections
	public int getPrescribedTestsCount() {
		return prescribedTests.size();
	}

	public void setHistorys(Set<History> historys) {
		this.historys = historys;
	}

	public Set<History> getHistorys() {
		return historys;
	}

	public void setPatient(com.oreon.cerebrum.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.cerebrum.patient.Patient getPatient() {

		return patient;

	}

	public void setPhysician(com.oreon.cerebrum.employee.Physician physician) {
		this.physician = physician;
	}

	public com.oreon.cerebrum.employee.Physician getPhysician() {

		return physician;

	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {

		return notes;

	}

	public void setPrescribedTests(Set<PrescribedTest> prescribedTests) {
		this.prescribedTests = prescribedTests;
	}

	public Set<PrescribedTest> getPrescribedTests() {
		return prescribedTests;
	}

	@Transient
	public String getDisplayName() {
		try {
			return historys + "";
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

		listSearchableFields.add("notes");

		listSearchableFields.add("historys.history");

		listSearchableFields.add("prescribedTests.remarks");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getNotes() + " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		if (getPhysician() != null)
			builder
					.append("physician:" + getPhysician().getDisplayName()
							+ " ");

		for (BusinessEntity e : historys) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : prescribedTests) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
