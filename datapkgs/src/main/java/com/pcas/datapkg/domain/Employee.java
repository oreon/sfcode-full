package com.pcas.datapkg.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Boost;
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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.model.support.audit.Auditable;

import org.witchcraft.utils.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.pcas.datapkg.ProjectUtils;

@Entity
@Table(name = "employee")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Name("employee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Employee extends com.pcas.datapkg.domain.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 2046415010L;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = true, updatable = true)
	@ContainedIn
	protected Department department

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String employeeNumber

	;

	@Column(unique = false)
	protected EmployeeType employeeType

	;

	@Column(unique = false)
	protected BigDecimal salary

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.pcas.datapkg.domain.inventory.Customer customer

	;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appUser_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.pcas.datapkg.users.AppUser appUser = new com.pcas.datapkg.users.AppUser();

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {

		return department;

	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeNumber() {

		return employeeNumber;

	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public EmployeeType getEmployeeType() {

		return employeeType;

	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getSalary() {

		return salary;

	}

	public void setCustomer(com.pcas.datapkg.domain.inventory.Customer customer) {
		this.customer = customer;
	}

	public com.pcas.datapkg.domain.inventory.Customer getCustomer() {

		return customer;

	}

	public void setAppUser(com.pcas.datapkg.users.AppUser appUser) {
		this.appUser = appUser;
	}

	public com.pcas.datapkg.users.AppUser getAppUser() {

		return appUser;

	}

	@Transient
	public String getDisplayName() {
		try {
			return super.getDisplayName();
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("employeeNumber");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getEmployeeNumber() + " ");

		if (getDepartment() != null)
			builder.append("department:" + getDepartment().getDisplayName()
					+ " ");

		if (getCustomer() != null)
			builder.append("customer:" + getCustomer().getDisplayName() + " ");

		if (getAppUser() != null)
			builder.append("appUser:" + getAppUser().getDisplayName() + " ");

		return builder.toString();
	}

}
