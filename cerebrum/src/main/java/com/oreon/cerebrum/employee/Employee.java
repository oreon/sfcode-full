package com.oreon.cerebrum.employee;

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

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "employee")
@Filters({@Filter(name = "archiveFilterDef"),

})
@Name("employee")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Employee extends com.oreon.cerebrum.patient.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -426154292L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String employeeNumber

	;

	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "createdBy_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> unusualOccurences = new HashSet<com.oreon.cerebrum.unusualoccurences.UnusualOccurence>();

	public void addUnusualOccurence(
			com.oreon.cerebrum.unusualoccurences.UnusualOccurence unusualOccurence) {
		unusualOccurence.setCreatedBy(this);
		this.unusualOccurences.add(unusualOccurence);
	}

	@Transient
	public List<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> getListUnusualOccurences() {
		return new ArrayList<com.oreon.cerebrum.unusualoccurences.UnusualOccurence>(
				unusualOccurences);
	}

	//JSF Friendly function to get count of collections
	public int getUnusualOccurencesCount() {
		return unusualOccurences.size();
	}

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appUser_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.users.AppUser appUser = new com.oreon.cerebrum.users.AppUser();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "facility_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.facility.Facility facility

	;

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeNumber() {

		return employeeNumber;

	}

	public void setUnusualOccurences(
			Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> unusualOccurences) {
		this.unusualOccurences = unusualOccurences;
	}

	public Set<com.oreon.cerebrum.unusualoccurences.UnusualOccurence> getUnusualOccurences() {
		return unusualOccurences;
	}

	public void setAppUser(com.oreon.cerebrum.users.AppUser appUser) {
		this.appUser = appUser;
	}

	public com.oreon.cerebrum.users.AppUser getAppUser() {

		return appUser;

	}

	public void setFacility(com.oreon.cerebrum.facility.Facility facility) {
		this.facility = facility;
	}

	public com.oreon.cerebrum.facility.Facility getFacility() {

		return facility;

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

		if (getAppUser() != null)
			builder.append("appUser:" + getAppUser().getDisplayName() + " ");

		if (getFacility() != null)
			builder.append("facility:" + getFacility().getDisplayName() + " ");

		for (BaseEntity e : unusualOccurences) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
