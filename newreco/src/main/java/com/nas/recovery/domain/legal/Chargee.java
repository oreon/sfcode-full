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
@Table(name = "chargee")
@Name("chargee")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Chargee extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1796066820L;

	@Field(index = Index.TOKENIZED)
	protected String firstName;

	@Field(index = Index.TOKENIZED)
	protected String lastName;

	@Field(index = Index.TOKENIZED)
	protected String phone;

	@Field(index = Index.TOKENIZED)
	protected String email;

	@Field(index = Index.TOKENIZED)
	protected String company;

	protected Double amount;

	protected Integer priority;

	protected ChargeeType chargeeType;

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

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {

		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {

		return email;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {

		return company;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getPriority() {

		return priority;
	}

	public void setChargeeType(ChargeeType chargeeType) {
		this.chargeeType = chargeeType;
	}

	public ChargeeType getChargeeType() {

		return chargeeType;
	}

	@Transient
	public String getDisplayName() {
		return firstName;
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

		listSearchableFields.add("phone");

		listSearchableFields.add("email");

		listSearchableFields.add("company");

		return listSearchableFields;
	}

}
