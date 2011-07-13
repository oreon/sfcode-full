package com.oreon.smartsis.domain;

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

import org.hibernate.annotations.Filter;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

@Entity
@Table(name = "paidfee")
@Filter(name = "archiveFilterDef")
@Name("paidFee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class PaidFee extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1971401482L;

	protected Double amount;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String notes;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", nullable = false, updatable = true)
	@ContainedIn
	protected Student student;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "gradeFee_id", nullable = false, updatable = true)
	@ContainedIn
	protected GradeFee gradeFee;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "gradeFeesInstance_id", nullable = false, updatable = true)
	@ContainedIn
	protected GradeFeesInstance gradeFeesInstance;

	protected FeeMonth month;

	protected Long year;

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;

	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {

		return notes;

	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {

		return student;

	}

	public void setGradeFee(GradeFee gradeFee) {
		this.gradeFee = gradeFee;
	}

	public GradeFee getGradeFee() {

		return gradeFee;

	}

	public void setGradeFeesInstance(GradeFeesInstance gradeFeesInstance) {
		this.gradeFeesInstance = gradeFeesInstance;
	}

	public GradeFeesInstance getGradeFeesInstance() {

		return gradeFeesInstance;

	}

	public void setMonth(FeeMonth month) {
		this.month = month;
	}

	public FeeMonth getMonth() {

		return month;

	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getYear() {

		return year;

	}

	@Transient
	public String getDisplayName() {
		try {
			return amount + "";
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

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getNotes() + " ");

		if (getStudent() != null)
			builder.append("student:" + getStudent().getDisplayName() + " ");

		if (getGradeFee() != null)
			builder.append("gradeFee:" + getGradeFee().getDisplayName() + " ");

		if (getGradeFeesInstance() != null)
			builder.append("gradeFeesInstance:"
					+ getGradeFeesInstance().getDisplayName() + " ");

		return builder.toString();
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
