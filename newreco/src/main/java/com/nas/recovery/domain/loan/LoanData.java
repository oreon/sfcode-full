package com.nas.recovery.domain.loan;

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
@Table(name = "loandata")
@Name("loanData")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class LoanData extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -433567230L;

	protected Double fees;

	protected Double originalAppraisedValue;

	@Field(index = Index.TOKENIZED)
	protected String taxPortion;

	protected Integer mortgageNumber;

	protected Double interestRate;

	protected Double arrears;

	protected Date fundingDate;

	protected Boolean condo;

	protected Double totalOutstanding;

	protected Double mortgageBalance;

	protected Date maturityDate;

	@Field(index = Index.TOKENIZED)
	protected String repaymentHistory;

	@Field(index = Index.TOKENIZED)
	protected String originalPurposeTransaction;

	protected Double taxes;

	protected Boolean rentalProperty;

	//borrowers-> ->->LoanData->

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "loanData_ID", nullable = false)
	@IndexedEmbedded
	private Set<Borrower> borrowers = new HashSet<Borrower>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "lender_id", nullable = false, updatable = true)
	@ContainedIn
	protected Lender lender;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "mortgageInsurer_id", nullable = true, updatable = true)
	@ContainedIn
	protected MortgageInsurer mortgageInsurer;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "titleInsurer_id", nullable = true, updatable = true)
	@ContainedIn
	protected TitleInsurer titleInsurer;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "broker_id", nullable = true, updatable = true)
	@ContainedIn
	protected Broker broker;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "underwriter_id", nullable = true, updatable = true)
	@ContainedIn
	protected Underwriter underwriter;

	public void setFees(Double fees) {
		this.fees = fees;
	}

	public Double getFees() {

		return fees;
	}

	public void setOriginalAppraisedValue(Double originalAppraisedValue) {
		this.originalAppraisedValue = originalAppraisedValue;
	}

	public Double getOriginalAppraisedValue() {

		return originalAppraisedValue;
	}

	public void setTaxPortion(String taxPortion) {
		this.taxPortion = taxPortion;
	}

	public String getTaxPortion() {

		return taxPortion;
	}

	public void setMortgageNumber(Integer mortgageNumber) {
		this.mortgageNumber = mortgageNumber;
	}

	public Integer getMortgageNumber() {

		return mortgageNumber;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getInterestRate() {

		return interestRate;
	}

	public void setArrears(Double arrears) {
		this.arrears = arrears;
	}

	public Double getArrears() {

		return arrears;
	}

	public void setFundingDate(Date fundingDate) {
		this.fundingDate = fundingDate;
	}

	public Date getFundingDate() {

		return fundingDate;
	}

	public void setCondo(Boolean condo) {
		this.condo = condo;
	}

	public Boolean getCondo() {

		return condo;
	}

	public void setTotalOutstanding(Double totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public Double getTotalOutstanding() {

		return totalOutstanding;
	}

	public void setMortgageBalance(Double mortgageBalance) {
		this.mortgageBalance = mortgageBalance;
	}

	public Double getMortgageBalance() {

		return mortgageBalance;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getMaturityDate() {

		return maturityDate;
	}

	public void setRepaymentHistory(String repaymentHistory) {
		this.repaymentHistory = repaymentHistory;
	}

	public String getRepaymentHistory() {

		return repaymentHistory;
	}

	public void setOriginalPurposeTransaction(String originalPurposeTransaction) {
		this.originalPurposeTransaction = originalPurposeTransaction;
	}

	public String getOriginalPurposeTransaction() {

		return originalPurposeTransaction;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getTaxes() {

		return taxes;
	}

	public void setRentalProperty(Boolean rentalProperty) {
		this.rentalProperty = rentalProperty;
	}

	public Boolean getRentalProperty() {

		return rentalProperty;
	}

	public void setBorrowers(Set<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

	public Set<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setLender(Lender lender) {
		this.lender = lender;
	}

	public Lender getLender() {

		return lender;
	}

	public void setMortgageInsurer(MortgageInsurer mortgageInsurer) {
		this.mortgageInsurer = mortgageInsurer;
	}

	public MortgageInsurer getMortgageInsurer() {

		return mortgageInsurer;
	}

	public void setTitleInsurer(TitleInsurer titleInsurer) {
		this.titleInsurer = titleInsurer;
	}

	public TitleInsurer getTitleInsurer() {

		return titleInsurer;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public Broker getBroker() {

		return broker;
	}

	public void setUnderwriter(Underwriter underwriter) {
		this.underwriter = underwriter;
	}

	public Underwriter getUnderwriter() {

		return underwriter;
	}

	@Transient
	public String getDisplayName() {
		return taxPortion;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("taxPortion");

		listSearchableFields.add("repaymentHistory");

		listSearchableFields.add("originalPurposeTransaction");

		return listSearchableFields;
	}

}
