package com.oreon.cerebrum.ddx;

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
@Table(name = "patientdiffdx")
@Filters({@Filter(name = "archiveFilterDef"),

})
@Name("patientDiffDx")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class PatientDiffDx extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1910013848L;

	@OneToMany(mappedBy = "patientDiffDx", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patientDiffDx_ID", nullable = true)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<PatientFinding> patientFindings = new HashSet<PatientFinding>();

	public void addPatientFinding(PatientFinding patientFinding) {
		patientFinding.setPatientDiffDx(this);
		this.patientFindings.add(patientFinding);
	}

	@Transient
	public List<com.oreon.cerebrum.ddx.PatientFinding> getListPatientFindings() {
		return new ArrayList<com.oreon.cerebrum.ddx.PatientFinding>(
				patientFindings);
	}

	//JSF Friendly function to get count of collections
	public int getPatientFindingsCount() {
		return patientFindings.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.cerebrum.patient.Patient patient

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String notes

	;

	public void setPatientFindings(Set<PatientFinding> patientFindings) {
		this.patientFindings = patientFindings;
	}

	public Set<PatientFinding> getPatientFindings() {
		return patientFindings;
	}

	public void setPatient(com.oreon.cerebrum.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.cerebrum.patient.Patient getPatient() {

		return patient;

	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {

		return notes;

	}

	@Transient
	public String getDisplayName() {
		try {
			return patientFindings + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getNotesAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(notes.trim(),
					100, 200, "...");
		} catch (Exception e) {
			return notes != null ? notes : "";
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

		listSearchableFields.add("notes");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getNotes() + " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		for (BaseEntity e : patientFindings) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
