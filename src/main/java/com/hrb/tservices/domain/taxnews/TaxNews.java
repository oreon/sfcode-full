package com.hrb.tservices.domain.taxnews;

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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.hrb.tservices.ProjectUtils;

@Entity
@Table(name = "taxnews")
@Filter(name = "archiveFilterDef")
@Name("taxNews")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class TaxNews extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 82578222L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String title

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "newsCategory_id", nullable = false, updatable = true)
	@ContainedIn
	protected NewsCategory newsCategory

	;

	@Column(unique = false)
	protected Boolean inactive

	;

	@OneToMany(mappedBy = "taxNews", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "taxNews_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<TaxNewsTranslation> taxNewsTranslations = new HashSet<TaxNewsTranslation>();

	public void addTaxNewsTranslation(TaxNewsTranslation taxNewsTranslation) {
		taxNewsTranslation.setTaxNews(this);
		this.taxNewsTranslations.add(taxNewsTranslation);
	}

	@Transient
	public List<com.hrb.tservices.domain.taxnews.TaxNewsTranslation> getListTaxNewsTranslations() {
		return new ArrayList<com.hrb.tservices.domain.taxnews.TaxNewsTranslation>(
				taxNewsTranslations);
	}

	//JSF Friendly function to get count of collections
	public int getTaxNewsTranslationsCount() {
		return taxNewsTranslations.size();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;

	}

	public void setNewsCategory(NewsCategory newsCategory) {
		this.newsCategory = newsCategory;
	}

	public NewsCategory getNewsCategory() {

		return newsCategory;

	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public Boolean getInactive() {

		return inactive;

	}

	public void setTaxNewsTranslations(
			Set<TaxNewsTranslation> taxNewsTranslations) {
		this.taxNewsTranslations = taxNewsTranslations;
	}

	public Set<TaxNewsTranslation> getTaxNewsTranslations() {
		return taxNewsTranslations;
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

		listSearchableFields.add("taxNewsTranslations.title");

		listSearchableFields.add("taxNewsTranslations.link");

		listSearchableFields.add("taxNewsTranslations.text");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getTitle() + " ");

		if (getNewsCategory() != null)
			builder.append("newsCategory:" + getNewsCategory().getDisplayName()
					+ " ");

		for (BusinessEntity e : taxNewsTranslations) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
