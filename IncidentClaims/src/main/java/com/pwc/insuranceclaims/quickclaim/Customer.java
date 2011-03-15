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
@Table(name = "customer")
@Filter(name = "archiveFilterDef")
@Name("customer")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Customer extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1435495327L;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String firstName;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String lastName;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String address1;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String address2;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String city;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String state;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String zipCode;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "customer_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Policy> policys = new HashSet<Policy>();

	public void addPolicys(Policy policys) {
		policys.setCustomer(this);
		this.policys.add(policys);
	}

	@Transient
	public List<com.pwc.insuranceclaims.quickclaim.Policy> getListPolicys() {
		return new ArrayList<com.pwc.insuranceclaims.quickclaim.Policy>(policys);
	}

	//JSF Friendly function to get count of collections
	public int getPolicysCount() {
		return policys.size();
	}

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "customer_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Dependent> dependents = new HashSet<Dependent>();

	public void addDependents(Dependent dependents) {
		dependents.setCustomer(this);
		this.dependents.add(dependents);
	}

	@Transient
	public List<com.pwc.insuranceclaims.quickclaim.Dependent> getListDependents() {
		return new ArrayList<com.pwc.insuranceclaims.quickclaim.Dependent>(
				dependents);
	}

	//JSF Friendly function to get count of collections
	public int getDependentsCount() {
		return dependents.size();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {

		return firstName;

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {

		return lastName;

	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress1() {

		return address1;

	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress2() {

		return address2;

	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {

		return city;

	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {

		return state;

	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipCode() {

		return zipCode;

	}

	public void setPolicys(Set<Policy> policys) {
		this.policys = policys;
	}

	public Set<Policy> getPolicys() {
		return policys;
	}

	public void setDependents(Set<Dependent> dependents) {
		this.dependents = dependents;
	}

	public Set<Dependent> getDependents() {
		return dependents;
	}

	@Transient
	public String getDisplayName() {
		try {
			return firstName;
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

		listSearchableFields.add("firstName");

		listSearchableFields.add("lastName");

		listSearchableFields.add("address1");

		listSearchableFields.add("address2");

		listSearchableFields.add("city");

		listSearchableFields.add("state");

		listSearchableFields.add("zipCode");

		listSearchableFields.add("dependents.dependentName");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getFirstName() + " ");

		builder.append(getLastName() + " ");

		builder.append(getAddress1() + " ");

		builder.append(getAddress2() + " ");

		builder.append(getCity() + " ");

		builder.append(getState() + " ");

		builder.append(getZipCode() + " ");

		for (BusinessEntity e : policys) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : dependents) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
