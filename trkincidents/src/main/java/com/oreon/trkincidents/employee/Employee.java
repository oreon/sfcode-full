package com.oreon.trkincidents.employee;

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
@Table(name = "employee")
@Filter(name = "archiveFilterDef")
@Name("employee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Employee extends com.oreon.trkincidents.patient.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -426154292L;

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String employeeNumber;

	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "createdBy_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.trkincidents.incidents.Incident> incidentsCreated = new HashSet<com.oreon.trkincidents.incidents.Incident>();

	public void addIncidentsCreated(
			com.oreon.trkincidents.incidents.Incident incidentsCreated) {
		incidentsCreated.setCreatedBy(this);
		this.incidentsCreated.add(incidentsCreated);
	}

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.users.User user = new com.oreon.trkincidents.users.User();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = false, updatable = true)
	@ContainedIn
	protected Department department;

	@OneToMany(mappedBy = "responsibleEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "responsibleEmployee_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.trkincidents.incidents.Incident> incidentsResponsibleFor = new HashSet<com.oreon.trkincidents.incidents.Incident>();

	public void addIncidentsResponsibleFor(
			com.oreon.trkincidents.incidents.Incident incidentsResponsibleFor) {
		incidentsResponsibleFor.setResponsibleEmployee(this);
		this.incidentsResponsibleFor.add(incidentsResponsibleFor);
	}

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "primaryPhone", column = @Column(name = "contactDetails_primaryPhone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "contactDetails_secondaryPhone")),

			@AttributeOverride(name = "streetAddress", column = @Column(name = "contactDetails_streetAddress")),

			@AttributeOverride(name = "city", column = @Column(name = "contactDetails_city")),

			@AttributeOverride(name = "zip", column = @Column(name = "contactDetails_zip"))

	})
	protected ContactDetails contactDetails = new ContactDetails();

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeNumber() {

		return employeeNumber;

	}

	public void setIncidentsCreated(
			Set<com.oreon.trkincidents.incidents.Incident> incidentsCreated) {
		this.incidentsCreated = incidentsCreated;
	}

	public Set<com.oreon.trkincidents.incidents.Incident> getIncidentsCreated() {
		return incidentsCreated;
	}

	public void setUser(com.oreon.trkincidents.users.User user) {
		this.user = user;
	}

	public com.oreon.trkincidents.users.User getUser() {

		return user;

	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {

		return department;

	}

	public void setIncidentsResponsibleFor(
			Set<com.oreon.trkincidents.incidents.Incident> incidentsResponsibleFor) {
		this.incidentsResponsibleFor = incidentsResponsibleFor;
	}

	public Set<com.oreon.trkincidents.incidents.Incident> getIncidentsResponsibleFor() {
		return incidentsResponsibleFor;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactDetails getContactDetails() {

		return contactDetails;

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

		listSearchableFields.add("contactDetails.primaryPhone");

		listSearchableFields.add("contactDetails.secondaryPhone");

		listSearchableFields.add("contactDetails.streetAddress");

		listSearchableFields.add("contactDetails.city");

		listSearchableFields.add("contactDetails.zip");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getEmployeeNumber() + " ");

		if (getUser() != null)
			builder.append("user:" + getUser().getDisplayName() + " ");

		if (getDepartment() != null)
			builder.append("department:" + getDepartment().getDisplayName()
					+ " ");

		for (BusinessEntity e : incidentsCreated) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : incidentsResponsibleFor) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
