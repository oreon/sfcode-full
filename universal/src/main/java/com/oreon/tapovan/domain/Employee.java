package com.oreon.tapovan.domain;

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

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

import org.witchcraft.utils.*;

@Entity
@Table(name = "employee")
@Filter(name = "archiveFilterDef")
@Name("employee")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Employee extends com.oreon.tapovan.domain.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 2046415010L;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = true, updatable = true)
	@ContainedIn
	protected Department department;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String employeeNumber;

	protected EmployeeType employeeType;

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
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("employeeNumber");

		return listSearchableFields;
	}

}
