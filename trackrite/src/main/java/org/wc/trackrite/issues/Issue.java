package org.wc.trackrite.issues;

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
@Table(name = "issue")
@Name("issue")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Issue extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1766878641L;

	@NotNull
	@Length(min = 2, max = 50)
	@Column(name = "title", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String title;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String description;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "screenShot_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "screenShot_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "screenShot_data", length = 4194304))})
	protected FileAttachment screenShot = new FileAttachment();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id", nullable = false, updatable = false)
	@ContainedIn
	protected Project project;

	protected Status status = Status.Unassigned;

	protected Priority priority = Priority.CRITICAL_NOT_URGENT;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "developer_id", nullable = true, updatable = false)
	@ContainedIn
	protected org.wc.trackrite.domain.Employee developer;

	protected Date closeTime;

	protected Integer estimate;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;
	}

	public void setScreenShot(FileAttachment screenShot) {
		this.screenShot = screenShot;
	}

	public FileAttachment getScreenShot() {

		return screenShot;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {

		return project;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {

		return status;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Priority getPriority() {

		return priority;
	}

	public void setDeveloper(org.wc.trackrite.domain.Employee developer) {
		this.developer = developer;
	}

	public org.wc.trackrite.domain.Employee getDeveloper() {

		return developer;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Date getCloseTime() {

		return closeTime;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public Integer getEstimate() {

		return estimate;
	}

	@Transient
	public String getDisplayName() {
		return title;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("title");

		listSearchableFields.add("description");

		return listSearchableFields;
	}

	private Long processId;

	private String processName;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

}
