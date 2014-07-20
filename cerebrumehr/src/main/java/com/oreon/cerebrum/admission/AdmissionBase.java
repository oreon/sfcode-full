package com.oreon.cerebrum.admission;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.witchcraft.base.entity.BaseEntity;

//Impl 

/**
 * This file is generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 *
 */

/**
 * 
 *
 */

@MappedSuperclass
public class AdmissionBase extends BaseEntity {
	private static final long serialVersionUID = -919788764L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.patient.Patient patient = new com.oreon.cerebrum.patient.Patient();

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String admissionNote

	;

	@Column(name = "dischargeDate", unique = false)
	protected Date dischargeDate

	;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "admission_ID", nullable = false)
	@OrderBy("id DESC")
	private Set<BedStay> bedStays = new HashSet<BedStay>();

	public void addBedStay(BedStay bedStay) {

		bedStay.setAdmission((Admission) this);

		this.bedStays.add(bedStay);
	}

	@Transient
	public List<com.oreon.cerebrum.admission.BedStay> getListBedStays() {
		return new ArrayList<com.oreon.cerebrum.admission.BedStay>(bedStays);
	}

	//JSF Friendly function to get count of collections
	public int getBedStaysCount() {
		return bedStays.size();
	}

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String dischargeNote

	;

	@Column(unique = false)
	protected com.oreon.cerebrum.patient.DischargeCode dischargeCode

	;

	@Transient
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String currentBed

	;

	@Column(unique = false)
	protected Boolean isCurrent

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id", nullable = true, updatable = true)
	protected com.oreon.cerebrum.billing.Invoice invoice

	;

	public void setPatient(com.oreon.cerebrum.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.cerebrum.patient.Patient getPatient() {

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

	public void setDischargeNote(String dischargeNote) {
		this.dischargeNote = dischargeNote;
	}

	public String getDischargeNote() {

		return dischargeNote;

	}

	public void setDischargeCode(
			com.oreon.cerebrum.patient.DischargeCode dischargeCode) {
		this.dischargeCode = dischargeCode;
	}

	public com.oreon.cerebrum.patient.DischargeCode getDischargeCode() {

		return dischargeCode;

	}

	public void setCurrentBed(String currentBed) {
		this.currentBed = currentBed;
	}

	public String getCurrentBed() {

		try {
			return patient.getBed().getDisplayName();
		} catch (Exception e) {

			return "";

		}

	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Boolean getIsCurrent() {

		return isCurrent;

	}

	public void setInvoice(com.oreon.cerebrum.billing.Invoice invoice) {
		this.invoice = invoice;
	}

	public com.oreon.cerebrum.billing.Invoice getInvoice() {

		return invoice;

	}

	@Transient
	public String getDisplayName() {
		try {
			return currentBed;
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

		listSearchableFields.add("currentBed");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getAdmissionNote() + " ");

		builder.append(getDischargeNote() + " ");

		builder.append(getCurrentBed() + " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		if (getInvoice() != null)
			builder.append("invoice:" + getInvoice().getDisplayName() + " ");

		for (BaseEntity e : bedStays) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	/*
	<param name="patientId" value="#{patientId}" />
	<param name="invoiceId" value="#{invoiceId}" />
	
	 */

}
