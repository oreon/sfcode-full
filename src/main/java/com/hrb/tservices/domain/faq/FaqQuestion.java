package com.hrb.tservices.domain.faq;

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
@Table(name = "faqquestion")
@Filter(name = "archiveFilterDef")
@Name("faqQuestion")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class FaqQuestion extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1851096772L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "faqCategory_id", nullable = false, updatable = true)
	@ContainedIn
	protected FaqCategory faqCategory

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String title

	;

	@OneToMany(mappedBy = "faqQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "faqQuestion_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<QuestionTranslation> questionTranslations = new HashSet<QuestionTranslation>();

	public void addQuestionTranslation(QuestionTranslation questionTranslation) {
		questionTranslation.setFaqQuestion(this);
		this.questionTranslations.add(questionTranslation);
	}

	@Transient
	public List<com.hrb.tservices.domain.faq.QuestionTranslation> getListQuestionTranslations() {
		return new ArrayList<com.hrb.tservices.domain.faq.QuestionTranslation>(
				questionTranslations);
	}

	//JSF Friendly function to get count of collections
	public int getQuestionTranslationsCount() {
		return questionTranslations.size();
	}

	@OneToMany(mappedBy = "faqQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "faqQuestion_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Rating> ratings = new HashSet<Rating>();

	public void addRating(Rating rating) {
		rating.setFaqQuestion(this);
		this.ratings.add(rating);
	}

	@Transient
	public List<com.hrb.tservices.domain.faq.Rating> getListRatings() {
		return new ArrayList<com.hrb.tservices.domain.faq.Rating>(ratings);
	}

	//JSF Friendly function to get count of collections
	public int getRatingsCount() {
		return ratings.size();
	}

	@Formula(value = "(select avg(r.rating) from Rating r  where r.faqQuestion_id = id)")
	protected Integer avgRating

	;

	@Column(unique = false)
	protected Boolean inactive

	;

	@Formula(value = "(select count(*) from FaqQuestionMetrics m where m.faqQuestion_id = id and m.dateViewed is not null)")
	protected Integer views

	;

	public void setFaqCategory(FaqCategory faqCategory) {
		this.faqCategory = faqCategory;
	}

	public FaqCategory getFaqCategory() {

		return faqCategory;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;

	}

	public void setQuestionTranslations(
			Set<QuestionTranslation> questionTranslations) {
		this.questionTranslations = questionTranslations;
	}

	public Set<QuestionTranslation> getQuestionTranslations() {
		return questionTranslations;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setAvgRating(Integer avgRating) {
		this.avgRating = avgRating;
	}

	public Integer getAvgRating() {

		return avgRating;

	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public Boolean getInactive() {

		return inactive;

	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getViews() {

		return views;

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

		listSearchableFields.add("questionTranslations.title");

		listSearchableFields.add("questionTranslations.text");

		listSearchableFields.add("questionTranslations.link");

		listSearchableFields.add("questionTranslations.answer");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getTitle() + " ");

		if (getFaqCategory() != null)
			builder.append("faqCategory:" + getFaqCategory().getDisplayName()
					+ " ");

		for (BusinessEntity e : questionTranslations) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : ratings) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
