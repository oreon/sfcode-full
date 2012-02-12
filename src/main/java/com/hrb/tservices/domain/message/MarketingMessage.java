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
@Table(name = "marketingmessage")
@Filter(name = "archiveFilterDef")
@Name("marketingMessage")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class MarketingMessage extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 523021658L;

	@OneToMany(mappedBy = "marketingMessage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "marketingMessage_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<MessageTranslation> messageTranslations = new HashSet<MessageTranslation>();

	public void addMessageTranslation(MessageTranslation messageTranslation) {
		messageTranslation.setMarketingMessage(this);
		this.messageTranslations.add(messageTranslation);
	}

	@Transient
	public List<com.hrb.tservices.domain.message.MessageTranslation> getListMessageTranslations() {
		return new ArrayList<com.hrb.tservices.domain.message.MessageTranslation>(
				messageTranslations);
	}

	//JSF Friendly function to get count of collections
	public int getMessageTranslationsCount() {
		return messageTranslations.size();
	}

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String messageTitle

	;

	public void setMessageTranslations(
			Set<MessageTranslation> messageTranslations) {
		this.messageTranslations = messageTranslations;
	}

	public Set<MessageTranslation> getMessageTranslations() {
		return messageTranslations;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageTitle() {

		return messageTitle;

	}

	@Transient
	public String getDisplayName() {
		try {
			return messageTitle;
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

		listSearchableFields.add("messageTitle");

		listSearchableFields.add("messageTranslations.buttonText");

		listSearchableFields.add("messageTranslations.message");

		listSearchableFields.add("messageTranslations.hyperLink");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getMessageTitle() + " ");

		for (BusinessEntity e : messageTranslations) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
