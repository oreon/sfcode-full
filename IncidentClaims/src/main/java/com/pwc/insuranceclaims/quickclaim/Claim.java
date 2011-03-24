package com.pwc.insuranceclaims.quickclaim;

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
import org.hibernate.annotations.Cascade;

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

import org.hibernate.annotations.Filter;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

@Entity
@Table(name = "claim")
@Filter(name = "archiveFilterDef")
@Name("claim")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Claim extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 833484993L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "policy_id", nullable = false, updatable = true)
	@ContainedIn
	protected Policy policy;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String claimNumber;

	@NotNull
	@Column(name = "summary", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String summary;

	protected Date claimDate;

	@NotNull
	@Column(name = "claimAmount", unique = false)
	protected Double claimAmount;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String claimDescription;

	@Column(name = "status", unique = false)
	protected ClaimStatus status;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "claimPatient_id", nullable = false, updatable = true)
	@ContainedIn
	protected Dependent claimPatient;

	@OneToMany(mappedBy = "claim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "claim_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<ClaimDocument> claimDocuments = new HashSet<ClaimDocument>();

	public void addClaimDocuments(ClaimDocument claimDocuments) {
		claimDocuments.setClaim(this);
		this.claimDocuments.add(claimDocuments);
	}

	@Transient
	public List<com.pwc.insuranceclaims.quickclaim.ClaimDocument> getListClaimDocuments() {
		return new ArrayList<com.pwc.insuranceclaims.quickclaim.ClaimDocument>(
				claimDocuments);
	}

	//JSF Friendly function to get count of collections
	public int getClaimDocumentsCount() {
		return claimDocuments.size();
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "primaryDocument_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "primaryDocument_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "primaryDocument_data", length = 4194304))})
	protected FileAttachment primaryDocument = new FileAttachment();

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Policy getPolicy() {

		return policy;

	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimNumber() {

		return claimNumber;

	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {

		return summary;

	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public Date getClaimDate() {

		return claimDate;

	}

	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Double getClaimAmount() {

		return claimAmount;

	}

	public void setClaimDescription(String claimDescription) {
		this.claimDescription = claimDescription;
	}

	public String getClaimDescription() {

		return claimDescription;

	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}

	public ClaimStatus getStatus() {

		return status;

	}

	public void setClaimPatient(Dependent claimPatient) {
		this.claimPatient = claimPatient;
	}

	public Dependent getClaimPatient() {

		return claimPatient;

	}

	public void setClaimDocuments(Set<ClaimDocument> claimDocuments) {
		this.claimDocuments = claimDocuments;
	}

	public Set<ClaimDocument> getClaimDocuments() {
		return claimDocuments;
	}

	public void setPrimaryDocument(FileAttachment primaryDocument) {
		this.primaryDocument = primaryDocument;
	}

	public FileAttachment getPrimaryDocument() {

		return primaryDocument;

	}

	@Transient
	public String getDisplayName() {
		try {
			return claimNumber + " " + summary;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("claimNumber");

		listSearchableFields.add("summary");

		listSearchableFields.add("claimDescription");

		listSearchableFields.add("claimDocuments.documentType");

		listSearchableFields.add("claimDocuments.documentDescription");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getClaimNumber() + " ");

		builder.append(getSummary() + " ");

		builder.append(getClaimDescription() + " ");

		if (getPolicy() != null)
			builder.append("policy:" + getPolicy().getDisplayName() + " ");

		if (getClaimPatient() != null)
			builder.append("claimPatient:" + getClaimPatient().getDisplayName()
					+ " ");

		for (BusinessEntity e : claimDocuments) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
