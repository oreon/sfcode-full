package com.nas.recovery.domain.legal;

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

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

@MappedSuperclass
//@Indexed
public class Process extends BusinessEntity {

	protected Date completedDate;

	protected Date dueDate;

	protected Integer processNumber;

	protected Boolean documentAdded;

	protected Boolean notRequired;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String explanation;

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Date getCompletedDate() {

		return completedDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDueDate() {

		return dueDate;
	}

	public void setProcessNumber(Integer processNumber) {
		this.processNumber = processNumber;
	}

	public Integer getProcessNumber() {

		return processNumber;
	}

	public void setDocumentAdded(Boolean documentAdded) {
		this.documentAdded = documentAdded;
	}

	public Boolean getDocumentAdded() {

		return documentAdded;
	}

	public void setNotRequired(Boolean notRequired) {
		this.notRequired = notRequired;
	}

	public Boolean getNotRequired() {

		return notRequired;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getExplanation() {

		return explanation;
	}

	@Transient
	public String getDisplayName() {
		return completedDate + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("explanation");

		return listSearchableFields;
	}

}
