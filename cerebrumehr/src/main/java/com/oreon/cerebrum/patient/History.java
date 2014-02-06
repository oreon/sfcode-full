package com.oreon.cerebrum.patient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

@Embeddable
public class History implements java.io.Serializable {
	private static final long serialVersionUID = 178755245L;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String medicalHistory

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String socialHistory

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String familyHistory

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String medications

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String allergies

	;

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getMedicalHistory() {

		return medicalHistory;

	}

	public void setSocialHistory(String socialHistory) {
		this.socialHistory = socialHistory;
	}

	public String getSocialHistory() {

		return socialHistory;

	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getFamilyHistory() {

		return familyHistory;

	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public String getMedications() {

		return medications;

	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getAllergies() {

		return allergies;

	}

	@Transient
	public String getDisplayName() {
		try {
			return medicalHistory + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getMedicalHistoryAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(medicalHistory
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return medicalHistory != null ? medicalHistory : "";
		}
	}

	@Transient
	public String getSocialHistoryAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(socialHistory
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return socialHistory != null ? socialHistory : "";
		}
	}

	@Transient
	public String getFamilyHistoryAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(familyHistory
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return familyHistory != null ? familyHistory : "";
		}
	}

	@Transient
	public String getMedicationsAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(medications
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return medications != null ? medications : "";
		}
	}

	@Transient
	public String getAllergiesAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(allergies
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return allergies != null ? allergies : "";
		}
	}

}