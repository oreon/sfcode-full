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

	protected Date claimDate;

	protected Double claimAmount;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String claimDescription;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String claimPatient;

	@OneToMany(mappedBy = "claim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "claim_ID", nullable = false)
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

	public void setClaimPatient(String claimPatient) {
		this.claimPatient = claimPatient;
	}

	public String getClaimPatient() {

		return claimPatient;

	}

	public void setClaimDocuments(Set<ClaimDocument> claimDocuments) {
		this.claimDocuments = claimDocuments;
	}

	public Set<ClaimDocument> getClaimDocuments() {
		return claimDocuments;
	}

	@Transient
	public String getDisplayName() {
		try {
			return claimNumber;
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

		listSearchableFields.add("claimDescription");

		listSearchableFields.add("claimPatient");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getClaimNumber() + " ");

		builder.append(getClaimDescription() + " ");

		builder.append(getClaimPatient() + " ");

		if (getPolicy() != null)
			builder.append("policy:" + getPolicy().getDisplayName() + " ");

		for (BusinessEntity e : claimDocuments) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
