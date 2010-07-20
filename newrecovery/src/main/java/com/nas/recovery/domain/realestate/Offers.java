package com.nas.recovery.domain.realestate;

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
@Table(name = "offers")
@Name("offers")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Offers extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 769379393L;

	protected Date saleDate;

	protected Double signBackAmount;

	@Field(index = Index.TOKENIZED)
	protected String conditionExpiry;

	@Field(index = Index.TOKENIZED)
	protected String purchaser;

	@Field(index = Index.TOKENIZED)
	protected String comments;

	protected Double amount;

	@Field(index = Index.TOKENIZED)
	protected String status;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateListing_id", nullable = false, updatable = true)
	@ContainedIn
	protected RealEstateListing realEstateListing;

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Date getSaleDate() {

		return saleDate;
	}

	public void setSignBackAmount(Double signBackAmount) {
		this.signBackAmount = signBackAmount;
	}

	public Double getSignBackAmount() {

		return signBackAmount;
	}

	public void setConditionExpiry(String conditionExpiry) {
		this.conditionExpiry = conditionExpiry;
	}

	public String getConditionExpiry() {

		return conditionExpiry;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getPurchaser() {

		return purchaser;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {

		return comments;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	public void setRealEstateListing(RealEstateListing realEstateListing) {
		this.realEstateListing = realEstateListing;
	}

	public RealEstateListing getRealEstateListing() {

		return realEstateListing;
	}

	@Transient
	public String getDisplayName() {
		return conditionExpiry;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("conditionExpiry");

		listSearchableFields.add("purchaser");

		listSearchableFields.add("comments");

		listSearchableFields.add("status");

		return listSearchableFields;
	}

}
