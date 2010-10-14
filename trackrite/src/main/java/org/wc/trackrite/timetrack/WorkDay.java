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
@Table(name = "workday")
@Name("workDay")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class WorkDay extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1020745213L;

	//timeTrackingEntrys->workDay ->WorkDay->TimeTrackingEntry->TimeTrackingEntry

	@OneToMany(mappedBy = "workDay", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "workDay_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<TimeTrackingEntry> timeTrackingEntrys = new HashSet<TimeTrackingEntry>();

	@NotNull
	@Column(name = "date", unique = false)
	protected Date date;

	public void setTimeTrackingEntrys(Set<TimeTrackingEntry> timeTrackingEntrys) {
		this.timeTrackingEntrys = timeTrackingEntrys;
	}

	public Set<TimeTrackingEntry> getTimeTrackingEntrys() {
		return timeTrackingEntrys;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {

		return date;
	}

	@Transient
	public String getDisplayName() {
		return timeTrackingEntrys + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("timeTrackingEntrys.details");

		return listSearchableFields;
	}

}
