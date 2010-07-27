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
@Table(name = "realestatelisting")
@Name("realEstateListing")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class RealEstateListing extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -698191460L;

	protected Double listPrice;

	protected Double propertyTaxes;

	protected Double soldPrice;

	protected Integer lockBox;

	protected Double costPerDiem;

	protected Boolean vtbAccepted;

	protected Integer realEstateNumber;

	@Field(index = Index.TOKENIZED)
	protected String status;

	@Field(index = Index.TOKENIZED)
	protected String cmaOrdered;

	@Field(index = Index.TOKENIZED)
	protected String occupied;

	protected Double commission;

	protected Integer mlsNumber;

	protected Double condoFees;

	protected Date dateListed;

	protected Integer mortgageNumber;

	protected Date soldDate;

	protected Integer daysOnMarket;

	@Field(index = Index.TOKENIZED)
	protected String mlsComments;

	protected Date closingDate;

	protected Boolean vtbOffered;

	protected Double deposit;

	@Column(name = "expiryDate", unique = false)
	protected Date expiryDate;

	//listingSummarys-> ->->RealEstateListing->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_ID", nullable = true)
	@IndexedEmbedded
	private Set<ListingSummary> listingSummarys = new HashSet<ListingSummary>();

	//agentHistorys-> ->->RealEstateListing->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_ID", nullable = true)
	@IndexedEmbedded
	private Set<AgentHistory> agentHistorys = new HashSet<AgentHistory>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateBoard_id", nullable = false, updatable = true)
	@ContainedIn
	protected RealEstateBoard realEstateBoard;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_id", nullable = false, updatable = true)
	@ContainedIn
	protected RealEstateProperty realEstateProperty;

	//offerses->realEstateListing ->RealEstateListing->RealEstateListing->RealEstateListing

	@OneToMany(mappedBy = "realEstateListing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateListing_ID", nullable = true)
	@IndexedEmbedded
	private Set<Offers> offerses = new HashSet<Offers>();

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "master_id", nullable = true, updatable = true)
	@ContainedIn
	protected MasterAgent master;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subagent_id", nullable = true, updatable = true)
	@ContainedIn
	protected MasterAgent subagent;

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public Double getListPrice() {

		return listPrice;
	}

	public void setPropertyTaxes(Double propertyTaxes) {
		this.propertyTaxes = propertyTaxes;
	}

	public Double getPropertyTaxes() {

		return propertyTaxes;
	}

	public void setSoldPrice(Double soldPrice) {
		this.soldPrice = soldPrice;
	}

	public Double getSoldPrice() {

		return soldPrice;
	}

	public void setLockBox(Integer lockBox) {
		this.lockBox = lockBox;
	}

	public Integer getLockBox() {

		return lockBox;
	}

	public void setCostPerDiem(Double costPerDiem) {
		this.costPerDiem = costPerDiem;
	}

	public Double getCostPerDiem() {

		return costPerDiem;
	}

	public void setVtbAccepted(Boolean vtbAccepted) {
		this.vtbAccepted = vtbAccepted;
	}

	public Boolean getVtbAccepted() {

		return vtbAccepted;
	}

	public void setRealEstateNumber(Integer realEstateNumber) {
		this.realEstateNumber = realEstateNumber;
	}

	public Integer getRealEstateNumber() {

		return realEstateNumber;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	public void setCmaOrdered(String cmaOrdered) {
		this.cmaOrdered = cmaOrdered;
	}

	public String getCmaOrdered() {

		return cmaOrdered;
	}

	public void setOccupied(String occupied) {
		this.occupied = occupied;
	}

	public String getOccupied() {

		return occupied;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getCommission() {

		return commission;
	}

	public void setMlsNumber(Integer mlsNumber) {
		this.mlsNumber = mlsNumber;
	}

	public Integer getMlsNumber() {

		return mlsNumber;
	}

	public void setCondoFees(Double condoFees) {
		this.condoFees = condoFees;
	}

	public Double getCondoFees() {

		return condoFees;
	}

	public void setDateListed(Date dateListed) {
		this.dateListed = dateListed;
	}

	public Date getDateListed() {

		return dateListed;
	}

	public void setMortgageNumber(Integer mortgageNumber) {
		this.mortgageNumber = mortgageNumber;
	}

	public Integer getMortgageNumber() {

		return mortgageNumber;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	public Date getSoldDate() {

		return soldDate;
	}

	public void setDaysOnMarket(Integer daysOnMarket) {
		this.daysOnMarket = daysOnMarket;
	}

	public Integer getDaysOnMarket() {

		return daysOnMarket;
	}

	public void setMlsComments(String mlsComments) {
		this.mlsComments = mlsComments;
	}

	public String getMlsComments() {

		return mlsComments;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getClosingDate() {

		return closingDate;
	}

	public void setVtbOffered(Boolean vtbOffered) {
		this.vtbOffered = vtbOffered;
	}

	public Boolean getVtbOffered() {

		return vtbOffered;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getDeposit() {

		return deposit;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getExpiryDate() {

		return expiryDate;
	}

	public void setListingSummarys(Set<ListingSummary> listingSummarys) {
		this.listingSummarys = listingSummarys;
	}

	public Set<ListingSummary> getListingSummarys() {
		return listingSummarys;
	}

	public void setAgentHistorys(Set<AgentHistory> agentHistorys) {
		this.agentHistorys = agentHistorys;
	}

	public Set<AgentHistory> getAgentHistorys() {
		return agentHistorys;
	}

	public void setRealEstateBoard(RealEstateBoard realEstateBoard) {
		this.realEstateBoard = realEstateBoard;
	}

	public RealEstateBoard getRealEstateBoard() {

		return realEstateBoard;
	}

	public void setRealEstateProperty(RealEstateProperty realEstateProperty) {
		this.realEstateProperty = realEstateProperty;
	}

	public RealEstateProperty getRealEstateProperty() {

		return realEstateProperty;
	}

	public void setOfferses(Set<Offers> offerses) {
		this.offerses = offerses;
	}

	public Set<Offers> getOfferses() {
		return offerses;
	}

	public void setMaster(MasterAgent master) {
		this.master = master;
	}

	public MasterAgent getMaster() {

		return master;
	}

	public void setSubagent(MasterAgent subagent) {
		this.subagent = subagent;
	}

	public MasterAgent getSubagent() {

		return subagent;
	}

	@Transient
	public String getDisplayName() {
		return status;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("status");

		listSearchableFields.add("cmaOrdered");

		listSearchableFields.add("occupied");

		listSearchableFields.add("mlsComments");

		listSearchableFields.add("offerses.conditionExpiry");

		listSearchableFields.add("offerses.purchaser");

		listSearchableFields.add("offerses.comments");

		listSearchableFields.add("offerses.status");

		return listSearchableFields;
	}

}
