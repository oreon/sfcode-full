package com.oreon.incidents.incidents;

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

import com.oreon.incidents.ProjectUtils;

@Entity
@Table(name = "formfieldinstance")
@Filter(name = "archiveFilterDef")
@Name("formFieldInstance")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class FormFieldInstance extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1050165182L;

	@NotNull
	@Column(name = "value", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String value

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "formField_id", nullable = false, updatable = true)
	@ContainedIn
	@NotNull
	protected FormField formField

	;

	@Column(unique = false)
	protected Boolean boolValue

	;

	@NotNull
	@Column(name = "dateValue", unique = false)
	protected Date dateValue

	;

	@NotNull
	@Column(name = "enumOrdinal", unique = false)
	protected Integer enumOrdinal

	;

	@NotNull
	@Lob
	@Column(name = "description", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String description

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "incident_id", nullable = false, updatable = true)
	@ContainedIn
	@NotNull
	protected Incident incident

	;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {

		return value;

	}

	public void setFormField(FormField formField) {
		this.formField = formField;
	}

	public FormField getFormField() {

		return formField;

	}

	public void setBoolValue(Boolean boolValue) {
		this.boolValue = boolValue;
	}

	public Boolean getBoolValue() {

		return boolValue;

	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Date getDateValue() {

		return dateValue;

	}

	public void setEnumOrdinal(Integer enumOrdinal) {
		this.enumOrdinal = enumOrdinal;
	}

	public Integer getEnumOrdinal() {

		return enumOrdinal;

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;

	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Incident getIncident() {

		return incident;

	}

	@Transient
	public String getDisplayName() {
		try {
			return ProjectUtils.getVal(this);
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getDescriptionAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(description
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return description != null ? description : "";
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

		listSearchableFields.add("value");

		listSearchableFields.add("description");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getValue() + " ");

		builder.append(getDescription() + " ");

		if (getFormField() != null)
			builder
					.append("formField:" + getFormField().getDisplayName()
							+ " ");

		if (getIncident() != null)
			builder.append("incident:" + getIncident().getDisplayName() + " ");

		return builder.toString();
	}

}
