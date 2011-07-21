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
@Table(name = "studentpaidfee")
@Filter(name = "archiveFilterDef")
@Name("studentPaidFee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class StudentPaidFee extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = 866326586L;

	protected Double amountOwed;

	protected Double amountPaid;

	protected Double dueAmount;

	protected Date dateOfPayment;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.smartsis.domain.Student student;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.smartsis.domain.Grade grade;

	public void setAmountOwed(Double amountOwed) {
		this.amountOwed = amountOwed;
	}

	public Double getAmountOwed() {

		return amountOwed;

	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Double getAmountPaid() {

		return amountPaid;

	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Double getDueAmount() {

		return dueAmount;

	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public Date getDateOfPayment() {

		return dateOfPayment;

	}

	public void setStudent(com.oreon.smartsis.domain.Student student) {
		this.student = student;
	}

	public com.oreon.smartsis.domain.Student getStudent() {

		return student;

	}

	public void setGrade(com.oreon.smartsis.domain.Grade grade) {
		this.grade = grade;
	}

	public com.oreon.smartsis.domain.Grade getGrade() {

		return grade;

	}

	@Transient
	public String getDisplayName() {
		try {
			return amountOwed + "";
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

		if (getStudent() != null)
			builder.append("student:" + getStudent().getDisplayName() + " ");

		if (getGrade() != null)
			builder.append("grade:" + getGrade().getDisplayName() + " ");

		return builder.toString();
	}

}
