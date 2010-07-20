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
@Indexed
public class Process extends BusinessEntity {

	protected Date completedDate;

	protected Integer processNumber;

	protected Date expiryDate;

	@Field(index = Index.TOKENIZED)
	protected String reason;

	@Field(index = Index.TOKENIZED)
	protected String type;

	@Field(index = Index.TOKENIZED)
	protected String description;

	protected Integer legalNumber;

	protected Boolean documentAdded;

	protected Boolean skip;

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Date getCompletedDate() {

		return completedDate;
	}

	public void setProcessNumber(Integer processNumber) {
		this.processNumber = processNumber;
	}

	public Integer getProcessNumber() {

		return processNumber;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getExpiryDate() {

		return expiryDate;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {

		return reason;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {

		return type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;
	}

	public void setLegalNumber(Integer legalNumber) {
		this.legalNumber = legalNumber;
	}

	public Integer getLegalNumber() {

		return legalNumber;
	}

	public void setDocumentAdded(Boolean documentAdded) {
		this.documentAdded = documentAdded;
	}

	public Boolean getDocumentAdded() {

		return documentAdded;
	}

	public void setSkip(Boolean skip) {
		this.skip = skip;
	}

	public Boolean getSkip() {

		return skip;
	}

	@Transient
	public String getDisplayName() {
		return reason;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("reason");

		listSearchableFields.add("type");

		listSearchableFields.add("description");

		return listSearchableFields;
	}

}
