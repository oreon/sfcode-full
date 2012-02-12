package com.hrb.tservices.domain.message;

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
@Table(name = "messagetranslation")
@Filter(name = "archiveFilterDef")
@Name("messageTranslation")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class MessageTranslation extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -550251131L;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String buttonText

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String message

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String hyperLink

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "marketingMessage_id", nullable = false, updatable = true)
	@ContainedIn
	protected MarketingMessage marketingMessage

	;

	@Column(unique = false)
	protected com.hrb.tservices.domain.taxnews.Language language

	;

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getButtonText() {

		return buttonText;

	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {

		return message;

	}

	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}

	public String getHyperLink() {

		return hyperLink;

	}

	public void setMarketingMessage(MarketingMessage marketingMessage) {
		this.marketingMessage = marketingMessage;
	}

	public MarketingMessage getMarketingMessage() {

		return marketingMessage;

	}

	public void setLanguage(com.hrb.tservices.domain.taxnews.Language language) {
		this.language = language;
	}

	public com.hrb.tservices.domain.taxnews.Language getLanguage() {

		return language;

	}

	@Transient
	public String getDisplayName() {
		try {
			return buttonText;
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

		listSearchableFields.add("buttonText");

		listSearchableFields.add("message");

		listSearchableFields.add("hyperLink");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getButtonText() + " ");

		builder.append(getMessage() + " ");

		builder.append(getHyperLink() + " ");

		if (getMarketingMessage() != null)
			builder.append("marketingMessage:"
					+ getMarketingMessage().getDisplayName() + " ");

		return builder.toString();
	}

}
