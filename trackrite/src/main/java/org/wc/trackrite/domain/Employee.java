package org.wc.trackrite.domain;

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

@Entity
@Table(name = "employee")
@Name("employee")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Employee extends org.wc.trackrite.domain.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 2046415010L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = false, updatable = true)
	@ContainedIn
	protected Department department;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String employeeNumber;

	protected EmployeeType employeeType;

	//issues->developer ->Employee->Issue->Issue

	@OneToMany(mappedBy = "developer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "developer_ID", nullable = true)
	@IndexedEmbedded
	private Set<org.wc.trackrite.issues.Issue> issues = new HashSet<org.wc.trackrite.issues.Issue>();

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "primaryPhone", column = @Column(name = "home_primaryPhone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "home_secondaryPhone")),

			@AttributeOverride(name = "email", column = @Column(name = "home_email"))

	})
	protected ContactDetails home = new ContactDetails();

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "primaryPhone", column = @Column(name = "mailing_primaryPhone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "mailing_secondaryPhone")),

			@AttributeOverride(name = "email", column = @Column(name = "mailing_email"))

	})
	protected ContactDetails mailing = new ContactDetails();

	//projects->employees ->Employee->Project->Project

	@ManyToMany(mappedBy = "employees")
	private Set<org.wc.trackrite.issues.Project> projects = new HashSet<org.wc.trackrite.issues.Project>();

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

	public void setIssues(Set<org.wc.trackrite.issues.Issue> issues) {
		this.issues = issues;
	}

	public Set<org.wc.trackrite.issues.Issue> getIssues() {
		return issues;
	}

	public void setHome(ContactDetails home) {
		this.home = home;
	}

	public ContactDetails getHome() {

		return home;
	}

	public void setMailing(ContactDetails mailing) {
		this.mailing = mailing;
	}

	public ContactDetails getMailing() {

		return mailing;
	}

	public void setProjects(Set<org.wc.trackrite.issues.Project> projects) {
		this.projects = projects;
	}

	public Set<org.wc.trackrite.issues.Project> getProjects() {
		return projects;
	}

	@Transient
	public String getDisplayName() {
		return super.getDisplayName();
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("employeeNumber");

		listSearchableFields.add("home.primaryPhone");

		listSearchableFields.add("home.secondaryPhone");

		listSearchableFields.add("home.email");

		listSearchableFields.add("mailing.primaryPhone");

		listSearchableFields.add("mailing.secondaryPhone");

		listSearchableFields.add("mailing.email");

		return listSearchableFields;
	}

}
