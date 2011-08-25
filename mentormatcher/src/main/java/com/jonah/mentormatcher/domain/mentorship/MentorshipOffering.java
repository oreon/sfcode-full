package com.jonah.mentormatcher.domain.mentorship;

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
@Table(name = "mentorshipoffering")
@Filter(name = "archiveFilterDef")
@Name("mentorshipOffering")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class MentorshipOffering extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 577568966L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String title

	;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String description

	;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String keywords

	;

	protected Boolean inactive

	;

	protected OfferingType scope

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", nullable = false, updatable = true)
	@ContainedIn
	protected Category category

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "mentor_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.jonah.mentormatcher.domain.Employee mentor

	;

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

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {

		return keywords;

	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public Boolean getInactive() {

		return inactive;

	}

	public void setScope(OfferingType scope) {
		this.scope = scope;
	}

	public OfferingType getScope() {

		return scope;

	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {

		return category;

	}

	public void setMentor(com.jonah.mentormatcher.domain.Employee mentor) {
		this.mentor = mentor;
	}

	public com.jonah.mentormatcher.domain.Employee getMentor() {

		return mentor;

	}

	@Transient
	public String getDisplayName() {
		try {
			return title;
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

		listSearchableFields.add("title");

		listSearchableFields.add("description");

		listSearchableFields.add("keywords");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getTitle() + " ");

		builder.append(getDescription() + " ");

		builder.append(getKeywords() + " ");

		if (getCategory() != null)
			builder.append("category:" + getCategory().getDisplayName() + " ");

		if (getMentor() != null)
			builder.append("mentor:" + getMentor().getDisplayName() + " ");

		return builder.toString();
	}

}
