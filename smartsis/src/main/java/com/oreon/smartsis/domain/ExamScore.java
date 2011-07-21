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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.smartsis.ProjectUtils;

@Entity
@Table(name = "examscore")
@Filter(name = "archiveFilterDef")
@Name("examScore")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ExamScore extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = 211546687L;

	@Transient
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String examName;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "examInstance_id", nullable = false, updatable = true)
	@ContainedIn
	protected ExamInstance examInstance;

	@Transient
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String subject;

	@NotNull
	@Column(name = "marks", unique = false)
	protected Integer marks;

	@Transient
	protected Double percentage;

	@Column(name = "rank", unique = false)
	protected Integer rank;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", nullable = false, updatable = true)
	@ContainedIn
	protected Student student;

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamName() {

		try {
			return examInstance.getExam().getName();
		} catch (Exception e) {

			return "";

		}

	}

	public void setExamInstance(ExamInstance examInstance) {
		this.examInstance = examInstance;
	}

	public ExamInstance getExamInstance() {

		return examInstance;

	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {

		try {
			return examInstance.getGradeSubject().getSubject().getName();
		} catch (Exception e) {

			return "";

		}

	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public Integer getMarks() {

		return marks;

	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Double getPercentage() {

		try {
			return 100.0 * marks / examInstance.getExam().getMaxMarks();
		} catch (Exception e) {

			return 0.0;

		}

	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getRank() {

		return rank;

	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {

		return student;

	}

	@Transient
	public String getDisplayName() {
		try {
			return examName;
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

		listSearchableFields.add("examName");

		listSearchableFields.add("subject");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getExamName() + " ");

		builder.append(getSubject() + " ");

		if (getExamInstance() != null)
			builder.append("examInstance:" + getExamInstance().getDisplayName()
					+ " ");

		if (getStudent() != null)
			builder.append("student:" + getStudent().getDisplayName() + " ");

		return builder.toString();
	}

}
