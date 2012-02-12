package com.hrb.tservices.domain.metrics;

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

@MappedSuperclass
public class BaseMetrics extends BusinessEntity {
	private static final long serialVersionUID = 8965769L;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "restMethod_id", nullable = true, updatable = true)
	@ContainedIn
	protected RestMethod restMethod

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "partner_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.hrb.tservices.domain.department.Partner partner

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "clientType_id", nullable = true, updatable = true)
	@ContainedIn
	protected ClientType clientType

	;

	@Column(unique = false)
	protected Date date

	;

	@Column(unique = false)
	protected com.hrb.tservices.domain.taxnews.Language language

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String sessionId

	;

	public void setRestMethod(RestMethod restMethod) {
		this.restMethod = restMethod;
	}

	public RestMethod getRestMethod() {

		return restMethod;

	}

	public void setPartner(com.hrb.tservices.domain.department.Partner partner) {
		this.partner = partner;
	}

	public com.hrb.tservices.domain.department.Partner getPartner() {

		return partner;

	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public ClientType getClientType() {

		return clientType;

	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {

		return date;

	}

	public void setLanguage(com.hrb.tservices.domain.taxnews.Language language) {
		this.language = language;
	}

	public com.hrb.tservices.domain.taxnews.Language getLanguage() {

		return language;

	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {

		return sessionId;

	}

	@Transient
	public String getDisplayName() {
		try {
			return sessionId;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("sessionId");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getSessionId() + " ");

		if (getRestMethod() != null)
			builder.append("restMethod:" + getRestMethod().getDisplayName()
					+ " ");

		if (getPartner() != null)
			builder.append("partner:" + getPartner().getDisplayName() + " ");

		if (getClientType() != null)
			builder.append("clientType:" + getClientType().getDisplayName()
					+ " ");

		return builder.toString();
	}

}
