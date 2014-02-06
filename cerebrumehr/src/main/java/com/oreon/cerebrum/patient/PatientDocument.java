package com.oreon.cerebrum.patient;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;

@Entity
@Table(name = "patientdocument")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class PatientDocument extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -2128826023L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@Column(unique = false)
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "file_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "file_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "file_data", length = 4194304))})
	protected FileAttachment file = new FileAttachment();

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String notes

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	protected Patient patient

	;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setFile(FileAttachment file) {
		this.file = file;
	}

	public FileAttachment getFile() {

		return file;

	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {

		return notes;

	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {

		return patient;

	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
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

		listSearchableFields.add("name");

		listSearchableFields.add("notes");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		builder.append(getNotes() + " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		return builder.toString();
	}

}