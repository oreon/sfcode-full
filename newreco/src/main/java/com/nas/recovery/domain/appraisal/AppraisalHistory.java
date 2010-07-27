package com.nas.recovery.domain.appraisal;

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

@Entity
@Table(name = "appraisalhistory")
@Name("appraisalHistory")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class AppraisalHistory extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1159626918L;

	@Field(index = Index.TOKENIZED)
	protected String notes;

	protected Integer requestNumber;

	protected Double amount;

	protected Date requestDate;

	@Field(index = Index.TOKENIZED)
	protected String type;

	protected Boolean asImproved;

	protected Boolean reviewed;

	@Field(index = Index.TOKENIZED)
	protected String appraiser;

	protected Integer appraisalNumber;

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {

		return notes;
	}

	public void setRequestNumber(Integer requestNumber) {
		this.requestNumber = requestNumber;
	}

	public Integer getRequestNumber() {

		return requestNumber;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getRequestDate() {

		return requestDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {

		return type;
	}

	public void setAsImproved(Boolean asImproved) {
		this.asImproved = asImproved;
	}

	public Boolean getAsImproved() {

		return asImproved;
	}

	public void setReviewed(Boolean reviewed) {
		this.reviewed = reviewed;
	}

	public Boolean getReviewed() {

		return reviewed;
	}

	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
	}

	public String getAppraiser() {

		return appraiser;
	}

	public void setAppraisalNumber(Integer appraisalNumber) {
		this.appraisalNumber = appraisalNumber;
	}

	public Integer getAppraisalNumber() {

		return appraisalNumber;
	}

	@Transient
	public String getDisplayName() {
		return notes;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("notes");

		listSearchableFields.add("type");

		listSearchableFields.add("appraiser");

		return listSearchableFields;
	}

}
