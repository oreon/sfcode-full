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

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;

@Entity
@Table(name = "realestateproperty")
@Name("realEstateProperty")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class RealEstateProperty extends BusinessEntity
		implements
			Auditable,
			java.io.Serializable {
	private static final long serialVersionUID = 1293066841L;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String streetAddress;

	protected States state;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String zip;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String city;
	
	private Long processId;
	
	private String processName;

	//realEstateListings->realEstateProperty ->RealEstateProperty->RealEstateProperty->RealEstateProperty

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@OneToMany(mappedBy = "realEstateProperty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<RealEstateListing> realEstateListings = new ArrayList<RealEstateListing>();

	//tenantInfos->realEstateProperty ->RealEstateProperty->RealEstateProperty->RealEstateProperty

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<TenantInfo> tenantInfos = new ArrayList<TenantInfo>();

	//cmas-> ->->Cma->

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<Cma> cmas = new ArrayList<Cma>();

	//appraisals->realEstateProperty ->RealEstateProperty->Appraisal->Appraisal

	@OneToMany(mappedBy = "realEstateProperty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<com.nas.recovery.domain.appraisal.Appraisal> appraisals = new ArrayList<com.nas.recovery.domain.appraisal.Appraisal>();

	//filesUploadeds->realEstateProperty ->RealEstateProperty->FilesUploaded->FilesUploaded

	@OneToMany(mappedBy = "realEstateProperty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<FilesUploaded> filesUploadeds = new ArrayList<FilesUploaded>();

	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "insurer_id", nullable = true, updatable = true)
	@ContainedIn
	protected com.nas.recovery.domain.loan.MortgageInsurer insurer;

	protected PropetyStatus status;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String title;

	//inspections-> ->->Inspection->

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<com.nas.recovery.domain.propertymanagement.Inspection> inspections = new ArrayList<com.nas.recovery.domain.propertymanagement.Inspection>();

	//utilitiys-> ->->Utilitiy->

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<com.nas.recovery.domain.propertymanagement.Utilitiy> utilitiys = new ArrayList<com.nas.recovery.domain.propertymanagement.Utilitiy>();

	@OneToOne(mappedBy = "realEstateProperty", optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ContainedIn
	protected com.nas.recovery.domain.legal.Legal legal;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String ownerPrimaryPhone;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String ownerAlternativePhone;

	protected Integer numberOfOccupants;

	protected com.nas.recovery.domain.propertymanagement.VacancyStatus vacancyStatus;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String lockboxCode;

	protected com.nas.recovery.domain.propertymanagement.Occupancy occupancy;

	protected com.nas.recovery.domain.propertymanagement.InspectionFrequency inspectionFrequency;

	//requestForApprovals-> ->->RealEstateProperty->

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_ID", nullable = true)
	@IndexedEmbedded
	private List<com.nas.recovery.domain.propertymanagement.RequestForApproval> requestForApprovals = new ArrayList<com.nas.recovery.domain.propertymanagement.RequestForApproval>();

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress() {

		return streetAddress;
	}

	public void setState(States state) {
		this.state = state;
	}

	public States getState() {

		return state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {

		return zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {

		return city;
	}

	public void setRealEstateListings(List<RealEstateListing> realEstateListings) {
		this.realEstateListings = realEstateListings;
	}

	public List<RealEstateListing> getRealEstateListings() {
		return realEstateListings;
	}

	public void setTenantInfos(List<TenantInfo> tenantInfos) {
		this.tenantInfos = tenantInfos;
	}

	public List<TenantInfo> getTenantInfos() {
		return tenantInfos;
	}

	public void setCmas(List<Cma> cmas) {
		this.cmas = cmas;
	}

	public List<Cma> getCmas() {
		return cmas;
	}

	public void setAppraisals(
			List<com.nas.recovery.domain.appraisal.Appraisal> appraisals) {
		this.appraisals = appraisals;
	}

	public List<com.nas.recovery.domain.appraisal.Appraisal> getAppraisals() {
		return appraisals;
	}

	public void setFilesUploadeds(List<FilesUploaded> filesUploadeds) {
		this.filesUploadeds = filesUploadeds;
	}

	public List<FilesUploaded> getFilesUploadeds() {
		return filesUploadeds;
	}

	public void setInsurer(com.nas.recovery.domain.loan.MortgageInsurer insurer) {
		this.insurer = insurer;
	}

	public com.nas.recovery.domain.loan.MortgageInsurer getInsurer() {

		return insurer;
	}

	public void setStatus(PropetyStatus status) {
		this.status = status;
	}

	public PropetyStatus getStatus() {

		return status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;
	}

	public void setInspections(
			List<com.nas.recovery.domain.propertymanagement.Inspection> inspections) {
		this.inspections = inspections;
	}

	public List<com.nas.recovery.domain.propertymanagement.Inspection> getInspections() {
		return inspections;
	}

	public void setUtilitiys(
			List<com.nas.recovery.domain.propertymanagement.Utilitiy> utilitiys) {
		this.utilitiys = utilitiys;
	}

	public List<com.nas.recovery.domain.propertymanagement.Utilitiy> getUtilitiys() {
		return utilitiys;
	}

	public void setLegal(com.nas.recovery.domain.legal.Legal legal) {
		this.legal = legal;
	}

	public com.nas.recovery.domain.legal.Legal getLegal() {

		return legal;
	}

	public void setOwnerPrimaryPhone(String ownerPrimaryPhone) {
		this.ownerPrimaryPhone = ownerPrimaryPhone;
	}

	public String getOwnerPrimaryPhone() {

		return ownerPrimaryPhone;
	}

	public void setOwnerAlternativePhone(String ownerAlternativePhone) {
		this.ownerAlternativePhone = ownerAlternativePhone;
	}

	public String getOwnerAlternativePhone() {

		return ownerAlternativePhone;
	}

	public void setNumberOfOccupants(Integer numberOfOccupants) {
		this.numberOfOccupants = numberOfOccupants;
	}

	public Integer getNumberOfOccupants() {

		return numberOfOccupants;
	}

	public void setVacancyStatus(
			com.nas.recovery.domain.propertymanagement.VacancyStatus vacancyStatus) {
		this.vacancyStatus = vacancyStatus;
	}

	public com.nas.recovery.domain.propertymanagement.VacancyStatus getVacancyStatus() {

		return vacancyStatus;
	}

	public void setLockboxCode(String lockboxCode) {
		this.lockboxCode = lockboxCode;
	}

	public String getLockboxCode() {

		return lockboxCode;
	}

	public void setOccupancy(
			com.nas.recovery.domain.propertymanagement.Occupancy occupancy) {
		this.occupancy = occupancy;
	}

	public com.nas.recovery.domain.propertymanagement.Occupancy getOccupancy() {

		return occupancy;
	}

	public void setInspectionFrequency(
			com.nas.recovery.domain.propertymanagement.InspectionFrequency inspectionFrequency) {
		this.inspectionFrequency = inspectionFrequency;
	}

	public com.nas.recovery.domain.propertymanagement.InspectionFrequency getInspectionFrequency() {

		return inspectionFrequency;
	}

	public void setRequestForApprovals(
			List<com.nas.recovery.domain.propertymanagement.RequestForApproval> requestForApprovals) {
		this.requestForApprovals = requestForApprovals;
	}

	public List<com.nas.recovery.domain.propertymanagement.RequestForApproval> getRequestForApprovals() {
		return requestForApprovals;
	}

	@Transient
	public String getDisplayName() {
		return streetAddress + ", " + city + ", " + state;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("streetAddress");

		listSearchableFields.add("zip");

		listSearchableFields.add("city");

		listSearchableFields.add("title");

		listSearchableFields.add("ownerPrimaryPhone");

		listSearchableFields.add("ownerAlternativePhone");

		listSearchableFields.add("lockboxCode");

		listSearchableFields.add("tenantInfos.unit");

		listSearchableFields.add("tenantInfos.name");

		listSearchableFields.add("appraisals.specialInstruction");

		listSearchableFields.add("filesUploadeds.title");

		listSearchableFields.add("inspections.Observation");

		listSearchableFields.add("utilitiys.name");

		listSearchableFields.add("utilitiys.accountNumber");

		listSearchableFields.add("requestForApprovals.referenceNumber");

		listSearchableFields.add("requestForApprovals.contractor");

		listSearchableFields.add("requestForApprovals.item");

		listSearchableFields.add("requestForApprovals.details");

		return listSearchableFields;
	}

}
