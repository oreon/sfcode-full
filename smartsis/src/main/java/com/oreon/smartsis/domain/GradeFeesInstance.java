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
@Table(name = "gradefeesinstance")
@Filter(name = "archiveFilterDef")
@Name("gradeFeesInstance")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class GradeFeesInstance extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 665010777L;

	@OneToMany(mappedBy = "gradeFeesInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "gradeFeesInstance_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<PaidFee> paidFees = new HashSet<PaidFee>();

	public void addPaidFees(PaidFee paidFees) {
		paidFees.setGradeFeesInstance(this);
		this.paidFees.add(paidFees);
	}

	@Transient
	public List<com.oreon.smartsis.domain.PaidFee> getListPaidFees() {
		return new ArrayList<com.oreon.smartsis.domain.PaidFee>(paidFees);
	}

	//JSF Friendly function to get count of collections
	public int getPaidFeesCount() {
		return paidFees.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id", nullable = false, updatable = true)
	@ContainedIn
	protected Grade grade;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String period;

	public void setPaidFees(Set<PaidFee> paidFees) {
		this.paidFees = paidFees;
	}

	public Set<PaidFee> getPaidFees() {
		return paidFees;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Grade getGrade() {

		return grade;

	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPeriod() {

		return period;

	}

	@Transient
	public String getDisplayName() {
		try {
			return period;
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

		listSearchableFields.add("period");

		listSearchableFields.add("paidFees.notes");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getPeriod() + " ");

		if (getGrade() != null)
			builder.append("grade:" + getGrade().getDisplayName() + " ");

		for (BusinessEntity e : paidFees) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
