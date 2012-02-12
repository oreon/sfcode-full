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
@Table(name = "faqcategory")
@Filter(name = "archiveFilterDef")
@Name("faqCategory")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class FaqCategory extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1845513252L;

	@OneToMany(mappedBy = "faqCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "faqCategory_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<FaqQuestion> faqQuestions = new HashSet<FaqQuestion>();

	public void addFaqQuestion(FaqQuestion faqQuestion) {
		faqQuestion.setFaqCategory(this);
		this.faqQuestions.add(faqQuestion);
	}

	@Transient
	public List<com.hrb.tservices.domain.faq.FaqQuestion> getListFaqQuestions() {
		return new ArrayList<com.hrb.tservices.domain.faq.FaqQuestion>(
				faqQuestions);
	}

	//JSF Friendly function to get count of collections
	public int getFaqQuestionsCount() {
		return faqQuestions.size();
	}

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String frenchName

	;

	public void setFaqQuestions(Set<FaqQuestion> faqQuestions) {
		this.faqQuestions = faqQuestions;
	}

	public Set<FaqQuestion> getFaqQuestions() {
		return faqQuestions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;
	}

	public String getFrenchName() {

		return frenchName;

	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
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

		listSearchableFields.add("name");

		listSearchableFields.add("frenchName");

		listSearchableFields.add("faqQuestions.title");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		builder.append(getFrenchName() + " ");

		for (BusinessEntity e : faqQuestions) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
