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
@Table(name = "feeincrease")
@Name("feeIncrease")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class FeeIncrease extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 468544289L;

	@Field(index = Index.TOKENIZED)
	protected String feeIncreaseStatus;

	protected Integer feeIncreaseNumber;

	protected Integer appraisalNumber;

	protected Date feeIncreaseDate;

	protected Double amount;

	@Field(index = Index.TOKENIZED)
	protected String details;

	public void setFeeIncreaseStatus(String feeIncreaseStatus) {
		this.feeIncreaseStatus = feeIncreaseStatus;
	}

	public String getFeeIncreaseStatus() {

		return feeIncreaseStatus;
	}

	public void setFeeIncreaseNumber(Integer feeIncreaseNumber) {
		this.feeIncreaseNumber = feeIncreaseNumber;
	}

	public Integer getFeeIncreaseNumber() {

		return feeIncreaseNumber;
	}

	public void setAppraisalNumber(Integer appraisalNumber) {
		this.appraisalNumber = appraisalNumber;
	}

	public Integer getAppraisalNumber() {

		return appraisalNumber;
	}

	public void setFeeIncreaseDate(Date feeIncreaseDate) {
		this.feeIncreaseDate = feeIncreaseDate;
	}

	public Date getFeeIncreaseDate() {

		return feeIncreaseDate;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {

		return details;
	}

	@Transient
	public String getDisplayName() {
		return feeIncreaseStatus;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("feeIncreaseStatus");

		listSearchableFields.add("details");

		return listSearchableFields;
	}

}
