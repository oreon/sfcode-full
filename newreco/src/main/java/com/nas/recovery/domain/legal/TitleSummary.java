package com.nas.recovery.domain.legal;

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
@Table(name = "titlesummary")
@Name("titleSummary")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class TitleSummary extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -705256837L;

	protected Integer legalNumber;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String chargeHolder;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String priority;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String description;

	protected Double amount;

	public void setLegalNumber(Integer legalNumber) {
		this.legalNumber = legalNumber;
	}

	public Integer getLegalNumber() {

		return legalNumber;
	}

	public void setChargeHolder(String chargeHolder) {
		this.chargeHolder = chargeHolder;
	}

	public String getChargeHolder() {

		return chargeHolder;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPriority() {

		return priority;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;
	}

	@Transient
	public String getDisplayName() {
		return chargeHolder;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("chargeHolder");

		listSearchableFields.add("priority");

		listSearchableFields.add("description");

		return listSearchableFields;
	}

}
