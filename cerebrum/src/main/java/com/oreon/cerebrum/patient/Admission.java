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

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "admission")
@Filter(name = "archiveFilterDef")
@Name("admission")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Admission extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -560001760L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	@ContainedIn
	protected Patient patient = new Patient();

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String admissionNote

	;

	@Column(name = "dischargeDate", unique = false)
	protected Date dischargeDate

	;

	@OneToMany(mappedBy = "admission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "admission_ID", nullable = false)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<BedStay> bedStays = new HashSet<BedStay>();

	public void addBedStay(BedStay bedStay) {
		bedStay.setAdmission(this);
		this.bedStays.add(bedStay);
	}

	@Transient
	public List<com.oreon.cerebrum.patient.BedStay> getListBedStays() {
		return new ArrayList<com.oreon.cerebrum.patient.BedStay>(bedStays);
	}

	//JSF Friendly function to get count of collections
	public int getBedStaysCount() {
		return bedStays.size();
	}

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bed_id", nullable = true, updatable = true)
	@ContainedIn
	protected com.oreon.cerebrum.facility.Bed bed

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String dischargeNote

	;

	@Column(unique = false)
	protected DischargeCode dischargeCode

	;

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {

		return patient;

	}

	public void setAdmissionNote(String admissionNote) {
		this.admissionNote = admissionNote;
	}

	public String getAdmissionNote() {

		return admissionNote;

	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getDischargeDate() {

		return dischargeDate;

	}

	public void setBedStays(Set<BedStay> bedStays) {
		this.bedStays = bedStays;
	}

	public Set<BedStay> getBedStays() {
		return bedStays;
	}

	public void setBed(com.oreon.cerebrum.facility.Bed bed) {
		this.bed = bed;
	}

	public com.oreon.cerebrum.facility.Bed getBed() {

		return bed;

	}

	public void setDischargeNote(String dischargeNote) {
		this.dischargeNote = dischargeNote;
	}

	public String getDischargeNote() {

		return dischargeNote;

	}

	public void setDischargeCode(DischargeCode dischargeCode) {
		this.dischargeCode = dischargeCode;
	}

	public DischargeCode getDischargeCode() {

		return dischargeCode;

	}

	@Transient
	public String getDisplayName() {
		try {
			return patient + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getAdmissionNoteAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(admissionNote
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return admissionNote != null ? admissionNote : "";
		}
	}

	@Transient
	public String getDischargeNoteAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(dischargeNote
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return dischargeNote != null ? dischargeNote : "";
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

		listSearchableFields.add("admissionNote");

		listSearchableFields.add("dischargeNote");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getAdmissionNote() + " ");

		builder.append(getDischargeNote() + " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		if (getBed() != null)
			builder.append("bed:" + getBed().getDisplayName() + " ");

		for (BaseEntity e : bedStays) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
