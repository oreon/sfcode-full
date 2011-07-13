package com.oreon.smartsis.domain;

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

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

@Entity
@Table(name = "gradefee")
@Filter(name = "archiveFilterDef")
@Name("gradeFee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class GradeFee extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -465927165L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id", nullable = false, updatable = true)
	@ContainedIn
	protected Grade grade;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "fee_id", nullable = false, updatable = true)
	@ContainedIn
	protected Fee fee;

	@Column(name = "amount", unique = false)
	protected Double amount;

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Grade getGrade() {

		return grade;

	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public Fee getFee() {

		return fee;

	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {

		return amount;

	}

	@Transient
	public String getDisplayName() {
		try {
			return fee.name;
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

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getGrade() != null)
			builder.append("grade:" + getGrade().getDisplayName() + " ");

		if (getFee() != null)
			builder.append("fee:" + getFee().getDisplayName() + " ");

		return builder.toString();
	}

}
