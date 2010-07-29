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
	protected String courtNumber;

	@Field(index = Index.TOKENIZED)
	protected String specialHandling;

	protected Integer mortgageNumber;

	protected Integer legalNumber;

	@Field(index = Index.TOKENIZED)
	protected String status;

	protected Boolean litigation;

	@Field(index = Index.TOKENIZED)
	protected String legalDescription;

	@Field(index = Index.TOKENIZED)
	protected String legalFileNumber;

	@Field(index = Index.TOKENIZED)
	protected String pin;

	@Field(index = Index.TOKENIZED)
	protected String pid;

	//bankruptcys-> ->->Legal->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_ID", nullable = true)
	@IndexedEmbedded
	private Set<Bankruptcy> bankruptcys = new HashSet<Bankruptcy>();

	//titleSummarys-> ->->TitleSummary->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_ID", nullable = true)
	@IndexedEmbedded
	private Set<TitleSummary> titleSummarys = new HashSet<TitleSummary>();

	//legalProcesses-> ->->LegalProcess->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_ID", nullable = false)
	@IndexedEmbedded
	private Set<LegalProcess> legalProcesses = new HashSet<LegalProcess>();

	//closingProcesses-> ->->ClosingProcess->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_ID", nullable = true)
	@IndexedEmbedded
	private Set<ClosingProcess> closingProcesses = new HashSet<ClosingProcess>();

	//insurerProcesses-> ->->InsurerProcess->

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_ID", nullable = true)
	@IndexedEmbedded
	private Set<InsurerProcess> insurerProcesses = new HashSet<InsurerProcess>();

	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}

	public String getCourtNumber() {

		return courtNumber;
	}

	public void setSpecialHandling(String specialHandling) {
		this.specialHandling = specialHandling;
	}

	public String getSpecialHandling() {

		return specialHandling;
	}

	public void setMortgageNumber(Integer mortgageNumber) {
		this.mortgageNumber = mortgageNumber;
	}

	public Integer getMortgageNumber() {

		return mortgageNumber;
	}

	public void setLegalNumber(Integer legalNumber) {
		this.legalNumber = legalNumber;
	}

	public Integer getLegalNumber() {

		return legalNumber;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	public void setLitigation(Boolean litigation) {
		this.litigation = litigation;
	}

	public Boolean getLitigation() {

		return litigation;
	}

	public void setLegalDescription(String legalDescription) {
		this.legalDescription = legalDescription;
	}

	public String getLegalDescription() {

		return legalDescription;
	}

	public void setLegalFileNumber(String legalFileNumber) {
		this.legalFileNumber = legalFileNumber;
	}

	public String getLegalFileNumber() {

		return legalFileNumber;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPin() {

		return pin;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {

		return pid;
	}

	public void setBankruptcys(Set<Bankruptcy> bankruptcys) {
		this.bankruptcys = bankruptcys;
	}

	public Set<Bankruptcy> getBankruptcys() {
		return bankruptcys;
	}

	public void setTitleSummarys(Set<TitleSummary> titleSummarys) {
		this.titleSummarys = titleSummarys;
	}

	public Set<TitleSummary> getTitleSummarys() {
		return titleSummarys;
	}

	public void setLegalProcesses(Set<LegalProcess> legalProcesses) {
		this.legalProcesses = legalProcesses;
	}

	public Set<LegalProcess> getLegalProcesses() {
		return legalProcesses;
	}

	public void setClosingProcesses(Set<ClosingProcess> closingProcesses) {
		this.closingProcesses = closingProcesses;
	}

	public Set<ClosingProcess> getClosingProcesses() {
		return closingProcesses;
	}

	public void setInsurerProcesses(Set<InsurerProcess> insurerProcesses) {
		this.insurerProcesses = insurerProcesses;
	}

	public Set<InsurerProcess> getInsurerProcesses() {
		return insurerProcesses;
	}

	@Transient
	public String getDisplayName() {
		return courtNumber;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("courtNumber");

		listSearchableFields.add("specialHandling");

		listSearchableFields.add("status");

		listSearchableFields.add("legalDescription");

		listSearchableFields.add("legalFileNumber");

		listSearchableFields.add("pin");

		listSearchableFields.add("pid");

		return listSearchableFields;
	}

}
