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

@MappedSuperclass
//@Indexed
public class Company extends BusinessEntity {

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String brand;

	@NotNull
	@Length(min = 2, max = 50)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String name;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String companyType;

	@IndexedEmbedded
	protected Address address = new Address();

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {

		return brand;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyType() {

		return companyType;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {

		return address;
	}

	@Transient
	public String getDisplayName() {
		return name;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("brand");

		listSearchableFields.add("name");

		listSearchableFields.add("companyType");

		listSearchableFields.add("address.streetDirection");

		listSearchableFields.add("address.streetName");

		listSearchableFields.add("address.province");

		listSearchableFields.add("address.streetType");

		listSearchableFields.add("address.postalCode");

		listSearchableFields.add("address.city");

		return listSearchableFields;
	}

}
