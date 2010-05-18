package com.nas.recoveryportal.appraisal;

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
import org.witchcraft.base.entity.*;
import org.witchcraft.model.support.audit.Auditable;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "project")
@Name("project")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Project extends BusinessEntity
		implements
			Auditable,
			java.io.Serializable {

	@NotNull
	@Length(min = 2, max = 50)
	@Field(index = Index.TOKENIZED)
	protected String name;

	@Lob
	protected String description;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "architect_id", nullable = false, updatable = true)
	@ContainedIn
	protected TeamMember architect;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", nullable = false, updatable = true)
	@ContainedIn
	protected TeamMember manager;

	//story->project ->Project->Story->Story

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Project_ID", nullable = true)
	@IndexedEmbedded
	private Set<Story> story = new HashSet<Story>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false, updatable = true)
	@ContainedIn
	protected Category category;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;
	}

	public void setArchitect(TeamMember architect) {
		this.architect = architect;
	}

	public TeamMember getArchitect() {

		return architect;
	}

	public void setManager(TeamMember manager) {
		this.manager = manager;
	}

	public TeamMember getManager() {

		return manager;
	}

	public void setStory(Set<Story> story) {
		this.story = story;
	}

	public Set<Story> getStory() {
		return story;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {

		return category;
	}

	@Transient
	public String getDisplayName() {
		return name;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		listSearchableFields.add("story.title");

		return listSearchableFields;
	}

}
