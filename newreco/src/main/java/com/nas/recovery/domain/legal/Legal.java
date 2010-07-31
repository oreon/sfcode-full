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

@Entity
@Table(name = "legal")
@Name("legal")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Legal extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1019597444L;

	@Field(index = Index.TOKENIZED)
	protected String legalFileNumber;

	@Field(index = Index.TOKENIZED)
	protected String courtNumber;

	protected Boolean litigation;

	protected SpecialHandling specialHandling;

	protected LegalStatus status;

	@Lob
	protected String legalDescription;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "lawyer_id", nullable = false, updatable = true)
	@ContainedIn
	protected Lawyer lawyer;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.nas.recovery.domain.realestate.RealEstateProperty realEstateProperty;

	//closingProcesses->legal ->Legal->ClosingProcess->ClosingProcess

	@OneToMany(mappedBy = "legal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "legal_ID", nullable = true)
	@IndexedEmbedded
	private Set<ClosingProcess> closingProcesses = new HashSet<ClosingProcess>();

	//legalProcesses->legal ->Legal->LegalProcess->LegalProcess

	@OneToMany(mappedBy = "legal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "legal_ID", nullable = true)
	@IndexedEmbedded
	private Set<LegalProcess> legalProcesses = new HashSet<LegalProcess>();

	//insurerProcesses->legal ->Legal->Legal->Legal

	@OneToMany(mappedBy = "legal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "legal_ID", nullable = true)
	@IndexedEmbedded
	private Set<InsurerProcess> insurerProcesses = new HashSet<InsurerProcess>();

	@Field(index = Index.TOKENIZED)
	protected String titleInsurer;

	protected Boolean titleInsuranceClaim;

	@Field(index = Index.TOKENIZED)
	protected String titleInsuranceClaimNumber;

	public void setLegalFileNumber(String legalFileNumber) {
		this.legalFileNumber = legalFileNumber;
	}

	public String getLegalFileNumber() {

		return legalFileNumber;
	}

	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}

	public String getCourtNumber() {

		return courtNumber;
	}

	public void setLitigation(Boolean litigation) {
		this.litigation = litigation;
	}

	public Boolean getLitigation() {

		return litigation;
	}

	public void setSpecialHandling(SpecialHandling specialHandling) {
		this.specialHandling = specialHandling;
	}

	public SpecialHandling getSpecialHandling() {

		return specialHandling;
	}

	public void setStatus(LegalStatus status) {
		this.status = status;
	}

	public LegalStatus getStatus() {

		return status;
	}

	public void setLegalDescription(String legalDescription) {
		this.legalDescription = legalDescription;
	}

	public String getLegalDescription() {

		return legalDescription;
	}

	public void setLawyer(Lawyer lawyer) {
		this.lawyer = lawyer;
	}

	public Lawyer getLawyer() {

		return lawyer;
	}

	public void setRealEstateProperty(
			com.nas.recovery.domain.realestate.RealEstateProperty realEstateProperty) {
		this.realEstateProperty = realEstateProperty;
	}

	public com.nas.recovery.domain.realestate.RealEstateProperty getRealEstateProperty() {

		return realEstateProperty;
	}

	public void setClosingProcesses(Set<ClosingProcess> closingProcesses) {
		this.closingProcesses = closingProcesses;
	}

	public Set<ClosingProcess> getClosingProcesses() {
		return closingProcesses;
	}

	public void setLegalProcesses(Set<LegalProcess> legalProcesses) {
		this.legalProcesses = legalProcesses;
	}

	public Set<LegalProcess> getLegalProcesses() {
		return legalProcesses;
	}

	public void setInsurerProcesses(Set<InsurerProcess> insurerProcesses) {
		this.insurerProcesses = insurerProcesses;
	}

	public Set<InsurerProcess> getInsurerProcesses() {
		return insurerProcesses;
	}

	public void setTitleInsurer(String titleInsurer) {
		this.titleInsurer = titleInsurer;
	}

	public String getTitleInsurer() {

		return titleInsurer;
	}

	public void setTitleInsuranceClaim(Boolean titleInsuranceClaim) {
		this.titleInsuranceClaim = titleInsuranceClaim;
	}

	public Boolean getTitleInsuranceClaim() {

		return titleInsuranceClaim;
	}

	public void setTitleInsuranceClaimNumber(String titleInsuranceClaimNumber) {
		this.titleInsuranceClaimNumber = titleInsuranceClaimNumber;
	}

	public String getTitleInsuranceClaimNumber() {

		return titleInsuranceClaimNumber;
	}

	@Transient
	public String getDisplayName() {
		return legalFileNumber;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("legalFileNumber");

		listSearchableFields.add("courtNumber");

		listSearchableFields.add("titleInsurer");

		listSearchableFields.add("titleInsuranceClaimNumber");

		return listSearchableFields;
	}

}
