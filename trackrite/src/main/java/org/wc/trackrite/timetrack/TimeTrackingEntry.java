package org.wc.trackrite.timetrack;

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
@Table(name = "timetrackingentry")
@Name("timeTrackingEntry")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class TimeTrackingEntry extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -96082530L;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", nullable = false, updatable = true)
	@ContainedIn
	protected org.wc.trackrite.domain.Employee employee;

	@NotNull
	@Column(name = "hours", unique = false)
	protected Integer hours;

	@NotNull
	@Column(name = "details", unique = false)
	@Field(index = Index.TOKENIZED)
	protected String details;

	@NotNull
	@Column(name = "date", unique = false)
	protected Date date;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id", nullable = false, updatable = true)
	@ContainedIn
	protected org.wc.trackrite.issues.Project project;

	public void setEmployee(org.wc.trackrite.domain.Employee employee) {
		this.employee = employee;
	}

	public org.wc.trackrite.domain.Employee getEmployee() {

		return employee;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getHours() {

		return hours;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {

		return details;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {

		return date;
	}

	public void setProject(org.wc.trackrite.issues.Project project) {
		this.project = project;
	}

	public org.wc.trackrite.issues.Project getProject() {

		return project;
	}

	@Transient
	public String getDisplayName() {
		return details;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("details");

		return listSearchableFields;
	}

}