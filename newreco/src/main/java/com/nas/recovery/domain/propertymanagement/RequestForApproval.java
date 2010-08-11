package com.nas.recovery.domain.propertymanagement;

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
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "requestforapproval")
@Name("requestForApproval")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class RequestForApproval extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 734462825L;

	protected Date requestDate;

	@Field(index = Index.TOKENIZED)
	protected String referenceNumber;

	protected Date recievedDate;

	@Field(index = Index.TOKENIZED)
	protected String contractor;

	@Field(index = Index.TOKENIZED)
	protected String item;

	protected double estimate;

	@Lob
	protected String details;

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getRequestDate() {

		return requestDate;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getReferenceNumber() {

		return referenceNumber;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public Date getRecievedDate() {

		return recievedDate;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public String getContractor() {

		return contractor;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItem() {

		return item;
	}

	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}

	public double getEstimate() {

		return estimate;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {

		return details;
	}

	@Transient
	public String getDisplayName() {
		return referenceNumber;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("referenceNumber");

		listSearchableFields.add("contractor");

		listSearchableFields.add("item");

		return listSearchableFields;
	}

}
