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
@Table(name = "offer")
@Name("offer")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Offer extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1025889638L;

	protected Date offerDate;

	@Field(index = Index.TOKENIZED)
	protected String purchaser;

	protected Double amount;

	protected OfferCondition offerCondition;

	protected OfferStatus status;

	protected Double signBackAmount;

	protected Date conditionExpiry;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateListing_id", nullable = false, updatable = true)
	@ContainedIn
	protected RealEstateListing realEstateListing;

	protected Date closingDate;

	@Lob
	protected String comments;

	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	public Date getOfferDate() {

		return offerDate;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getPurchaser() {

		return purchaser;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;
	}

	public void setOfferCondition(OfferCondition offerCondition) {
		this.offerCondition = offerCondition;
	}

	public OfferCondition getOfferCondition() {

		return offerCondition;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}

	public OfferStatus getStatus() {

		return status;
	}

	public void setSignBackAmount(Double signBackAmount) {
		this.signBackAmount = signBackAmount;
	}

	public Double getSignBackAmount() {

		return signBackAmount;
	}

	public void setConditionExpiry(Date conditionExpiry) {
		this.conditionExpiry = conditionExpiry;
	}

	public Date getConditionExpiry() {

		return conditionExpiry;
	}

	public void setRealEstateListing(RealEstateListing realEstateListing) {
		this.realEstateListing = realEstateListing;
	}

	public RealEstateListing getRealEstateListing() {

		return realEstateListing;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getClosingDate() {

		return closingDate;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {

		return comments;
	}

	@Transient
	public String getDisplayName() {
		return purchaser;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("purchaser");

		return listSearchableFields;
	}

}
