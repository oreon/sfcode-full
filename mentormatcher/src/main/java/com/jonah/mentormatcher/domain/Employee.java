package com.jonah.mentormatcher.domain;

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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.jonah.mentormatcher.ProjectUtils;

@Entity
@Table(name = "employee")
@Filter(name = "archiveFilterDef")
@Name("employee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Employee extends com.jonah.mentormatcher.domain.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 2046415010L;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = true, updatable = true)
	@ContainedIn
	protected Department department

	;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String employeeNumber

	;

	protected EmployeeType employeeType

	;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "picture_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "picture_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "picture_data", length = 4194304))})
	protected FileAttachment picture = new FileAttachment();

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "employee_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.jonah.mentormatcher.domain.mentorship.Testimonial> testimonials = new HashSet<com.jonah.mentormatcher.domain.mentorship.Testimonial>();

	public void addTestimonials(
			com.jonah.mentormatcher.domain.mentorship.Testimonial testimonials) {
		testimonials.setEmployee(this);
		this.testimonials.add(testimonials);
	}

	@Transient
	public List<com.jonah.mentormatcher.domain.mentorship.Testimonial> getListTestimonials() {
		return new ArrayList<com.jonah.mentormatcher.domain.mentorship.Testimonial>(
				testimonials);
	}

	//JSF Friendly function to get count of collections
	public int getTestimonialsCount() {
		return testimonials.size();
	}

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appUser_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.jonah.mentormatcher.domain.users.AppUser appUser = new com.jonah.mentormatcher.domain.users.AppUser();

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "designation_id", nullable = true, updatable = true)
	@ContainedIn
	protected Designation designation

	;

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

	public void setPicture(FileAttachment picture) {
		this.picture = picture;
	}

	public FileAttachment getPicture() {

		return picture;

	}

	public void setTestimonials(
			Set<com.jonah.mentormatcher.domain.mentorship.Testimonial> testimonials) {
		this.testimonials = testimonials;
	}

	public Set<com.jonah.mentormatcher.domain.mentorship.Testimonial> getTestimonials() {
		return testimonials;
	}

	public void setAppUser(com.jonah.mentormatcher.domain.users.AppUser appUser) {
		this.appUser = appUser;
	}

	public com.jonah.mentormatcher.domain.users.AppUser getAppUser() {

		return appUser;

	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Designation getDesignation() {

		return designation;

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

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getEmployeeNumber() + " ");

		if (getDepartment() != null)
			builder.append("department:" + getDepartment().getDisplayName()
					+ " ");

		if (getAppUser() != null)
			builder.append("appUser:" + getAppUser().getDisplayName() + " ");

		if (getDesignation() != null)
			builder.append("designation:" + getDesignation().getDisplayName()
					+ " ");

		for (BusinessEntity e : testimonials) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
