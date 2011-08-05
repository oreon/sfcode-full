package com.oreon.smartsis.fees;

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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.smartsis.ProjectUtils;

@Entity
@Table(name = "monthlyfee")
@Filter(name = "archiveFilterDef")
@Name("monthlyFee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class MonthlyFee extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = -2070081888L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.smartsis.domain.Grade grade

	;

	@OneToMany(mappedBy = "monthlyFee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "monthlyFee_ID", nullable = false)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<GradeFeeItem> gradeFeeItems = new HashSet<GradeFeeItem>();

	public void addGradeFeeItems(GradeFeeItem gradeFeeItems) {
		gradeFeeItems.setMonthlyFee(this);
		this.gradeFeeItems.add(gradeFeeItems);
	}

	@Transient
	public List<com.oreon.smartsis.fees.GradeFeeItem> getListGradeFeeItems() {
		return new ArrayList<com.oreon.smartsis.fees.GradeFeeItem>(
				gradeFeeItems);
	}

	//JSF Friendly function to get count of collections
	public int getGradeFeeItemsCount() {
		return gradeFeeItems.size();
	}

	@NotNull
	@Column(name = "month", unique = false)
	protected com.oreon.smartsis.domain.FeeMonth month

	;

	@Formula(value = "(select sum(gfi.amount) from gradefeeitem gfi where gfi.monthlyFee_id = id)")
	protected Double total

	;

	public void setGrade(com.oreon.smartsis.domain.Grade grade) {
		this.grade = grade;
	}

	public com.oreon.smartsis.domain.Grade getGrade() {

		return grade;

	}

	public void setGradeFeeItems(Set<GradeFeeItem> gradeFeeItems) {
		this.gradeFeeItems = gradeFeeItems;
	}

	public Set<GradeFeeItem> getGradeFeeItems() {
		return gradeFeeItems;
	}

	public void setMonth(com.oreon.smartsis.domain.FeeMonth month) {
		this.month = month;
	}

	public com.oreon.smartsis.domain.FeeMonth getMonth() {

		return month;

	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {

		return total;

	}

	@Transient
	public String getDisplayName() {
		try {
			return grade + "";
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

		for (BusinessEntity e : gradeFeeItems) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
