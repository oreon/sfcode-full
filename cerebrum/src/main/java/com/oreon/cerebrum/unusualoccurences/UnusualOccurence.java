package com.oreon.cerebrum.unusualoccurences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
@Table(name = "unusualoccurence")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Name("unusualOccurence")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class UnusualOccurence extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -2138847705L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "occurenceType_id", nullable = false, updatable = true)
	protected OccurenceType occurenceType

	;

	@Column(unique = false)
	protected TreatmentCategory category

	;

	@Column(unique = false)
	protected Severity severity

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String description

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.patient.Patient patient

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "createdBy_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.employee.Employee createdBy

	;

	public void setOccurenceType(OccurenceType occurenceType) {
		this.occurenceType = occurenceType;
	}

	public OccurenceType getOccurenceType() {

		return occurenceType;

	}

	public void setCategory(TreatmentCategory category) {
		this.category = category;
	}

	public TreatmentCategory getCategory() {

		return category;

	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Severity getSeverity() {

		return severity;

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;

	}

	public void setPatient(com.oreon.cerebrum.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.cerebrum.patient.Patient getPatient() {

		return patient;

	}

	public void setCreatedBy(com.oreon.cerebrum.employee.Employee createdBy) {
		this.createdBy = createdBy;
	}

	public com.oreon.cerebrum.employee.Employee getCreatedBy() {

		return createdBy;

	}

	@Transient
	public String getDisplayName() {
		try {
			return occurenceType + "";
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

		listSearchableFields.add("description");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getDescription() + " ");

		if (getOccurenceType() != null)
			builder.append("occurenceType:"
					+ getOccurenceType().getDisplayName() + " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		if (getCreatedBy() != null)
			builder
					.append("createdBy:" + getCreatedBy().getDisplayName()
							+ " ");

		return builder.toString();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	private Long processId;

	private String processName;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

}
