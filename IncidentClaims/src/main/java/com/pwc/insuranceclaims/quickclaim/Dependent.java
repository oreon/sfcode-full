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
@Table(name = "dependent")
@Filter(name = "archiveFilterDef")
@Name("dependent")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Dependent extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 708951580L;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String dependentName;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", nullable = false, updatable = true)
	@ContainedIn
	protected Customer customer;

	protected Date dependentDateofBirth;

	protected com.pwc.insuranceclaims.domain.Gender dependentGender;

	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}

	public String getDependentName() {

		return dependentName;

	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {

		return customer;

	}

	public void setDependentDateofBirth(Date dependentDateofBirth) {
		this.dependentDateofBirth = dependentDateofBirth;
	}

	public Date getDependentDateofBirth() {

		return dependentDateofBirth;

	}

	public void setDependentGender(
			com.pwc.insuranceclaims.domain.Gender dependentGender) {
		this.dependentGender = dependentGender;
	}

	public com.pwc.insuranceclaims.domain.Gender getDependentGender() {

		return dependentGender;

	}

	@Transient
	public String getDisplayName() {
		try {
			return dependentName;
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

		listSearchableFields.add("dependentName");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getDependentName() + " ");

		if (getCustomer() != null)
			builder.append("customer:" + getCustomer().getDisplayName() + " ");

		return builder.toString();
	}

}
