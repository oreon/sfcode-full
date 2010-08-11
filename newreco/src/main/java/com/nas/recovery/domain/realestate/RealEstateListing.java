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

	protected Date dateListed;

	@Column(name = "expiryDate", unique = false)
	protected Date expiryDate;

	protected Double listPrice;

	protected Double minSalePrice;

	protected Integer mlsNumber;

	protected Integer daysOnMarket;

	protected Double propertyTaxes;

	protected Double condoFees;

	protected Double costPerDiem;

	protected Integer lockBox;

	protected Double soldPrice;

	protected Boolean ownerOccupied;

	protected Boolean vacant;

	protected Boolean tenanted;

	@Lob
	protected String mlsComments;

	protected Integer realEstateNumber;

	@Field(index = Index.TOKENIZED)
	protected String cmaOrdered;

	@Field(index = Index.TOKENIZED)
	protected String occupied;

	protected Double commission;

	//listingSummarys-> ->->ListingSummary->

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateListing_ID", nullable = true)
	@IndexedEmbedded
	private Set<ListingSummary> listingSummarys = new HashSet<ListingSummary>();

	//agentHistorys-> ->->AgentHistory->

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateListing_ID", nullable = true)
	@IndexedEmbedded
	private Set<AgentHistory> agentHistorys = new HashSet<AgentHistory>();

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateBoard_id", nullable = false, updatable = true)
	@ContainedIn
	protected RealEstateBoard realEstateBoard;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_id", nullable = false, updatable = true)
	@ContainedIn
	protected RealEstateProperty realEstateProperty;

	//offers->realEstateListing ->RealEstateListing->Offer->Offer

	@OneToMany(mappedBy = "realEstateListing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateListing_ID", nullable = true)
	@IndexedEmbedded
	private Set<Offer> offers = new HashSet<Offer>();

	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "master_id", nullable = true, updatable = true)
	@ContainedIn
	protected MasterAgent master;

	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subagent_id", nullable = true, updatable = true)
	@ContainedIn
	protected MasterAgent subagent;

	protected Integer mortgageNumber;

	protected Date soldDate;

	protected Boolean vtbOffered;

	protected Boolean vtbAccepted;

	protected Double deposit;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "sale_id", nullable = false, updatable = true)
	@ContainedIn
	protected Sale sale;

	public void setDateListed(Date dateListed) {
		this.dateListed = dateListed;
	}

	public Date getDateListed() {

		return dateListed;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getExpiryDate() {

		return expiryDate;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public Double getListPrice() {

		return listPrice;
	}

	public void setMinSalePrice(Double minSalePrice) {
		this.minSalePrice = minSalePrice;
	}

	public Double getMinSalePrice() {

		return minSalePrice;
	}

	public void setMlsNumber(Integer mlsNumber) {
		this.mlsNumber = mlsNumber;
	}

	public Integer getMlsNumber() {

		return mlsNumber;
	}

	public void setDaysOnMarket(Integer daysOnMarket) {
		this.daysOnMarket = daysOnMarket;
	}

	public Integer getDaysOnMarket() {

		return daysOnMarket;
	}

	public void setPropertyTaxes(Double propertyTaxes) {
		this.propertyTaxes = propertyTaxes;
	}

	public Double getPropertyTaxes() {

		return propertyTaxes;
	}

	public void setCondoFees(Double condoFees) {
		this.condoFees = condoFees;
	}

	public Double getCondoFees() {

		return condoFees;
	}

	public void setCostPerDiem(Double costPerDiem) {
		this.costPerDiem = costPerDiem;
	}

	public Double getCostPerDiem() {

		return costPerDiem;
	}

	public void setLockBox(Integer lockBox) {
		this.lockBox = lockBox;
	}

	public Integer getLockBox() {

		return lockBox;
	}

	public void setSoldPrice(Double soldPrice) {
		this.soldPrice = soldPrice;
	}

	public Double getSoldPrice() {

		return soldPrice;
	}

	public void setOwnerOccupied(Boolean ownerOccupied) {
		this.ownerOccupied = ownerOccupied;
	}

	public Boolean getOwnerOccupied() {

		return ownerOccupied;
	}

	public void setVacant(Boolean vacant) {
		this.vacant = vacant;
	}

	public Boolean getVacant() {

		return vacant;
	}

	public void setTenanted(Boolean tenanted) {
		this.tenanted = tenanted;
	}

	public Boolean getTenanted() {

		return tenanted;
	}

	public void setMlsComments(String mlsComments) {
		this.mlsComments = mlsComments;
	}

	public String getMlsComments() {

		return mlsComments;
	}

	public void setRealEstateNumber(Integer realEstateNumber) {
		this.realEstateNumber = realEstateNumber;
	}

	public Integer getRealEstateNumber() {

		return realEstateNumber;
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

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<Offer> getOffers() {
		return offers;
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

	public void setVtbOffered(Boolean vtbOffered) {
		this.vtbOffered = vtbOffered;
	}

	public Boolean getVtbOffered() {

		return vtbOffered;
	}

	public void setVtbAccepted(Boolean vtbAccepted) {
		this.vtbAccepted = vtbAccepted;
	}

	public Boolean getVtbAccepted() {

		return vtbAccepted;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getDeposit() {

		return deposit;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Sale getSale() {

		return sale;
	}

	@Transient
	public String getDisplayName() {
		return cmaOrdered;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("cmaOrdered");

		listSearchableFields.add("occupied");

		listSearchableFields.add("offers.purchaser");

		return listSearchableFields;
	}

}
