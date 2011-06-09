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
@Table(name = "examinstance")
@Filter(name = "archiveFilterDef")
@Name("examInstance")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class ExamInstance extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 787476156L;

	@OneToMany(mappedBy = "examInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "examInstance_ID", nullable = true)
	@OrderBy("rank")
	@IndexedEmbedded
	private Set<ExamScore> examScores = new HashSet<ExamScore>();

	public void addExamScores(ExamScore examScores) {
		examScores.setExamInstance(this);
		this.examScores.add(examScores);
	}

	@Transient
	public List<com.oreon.smartsis.domain.ExamScore> getListExamScores() {
		return new ArrayList<com.oreon.smartsis.domain.ExamScore>(examScores);
	}

	//JSF Friendly function to get count of collections
	public int getExamScoresCount() {
		return examScores.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_id", nullable = false, updatable = true)
	@ContainedIn
	protected Exam exam;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "gradeSubject_id", nullable = false, updatable = true)
	@ContainedIn
	protected GradeSubject gradeSubject;

	protected Date dateHeld;

	@Column(name = "average", unique = false)
	protected Integer average;

	public void setExamScores(Set<ExamScore> examScores) {
		this.examScores = examScores;
	}

	public Set<ExamScore> getExamScores() {
		return examScores;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Exam getExam() {

		return exam;

	}

	public void setGradeSubject(GradeSubject gradeSubject) {
		this.gradeSubject = gradeSubject;
	}

	public GradeSubject getGradeSubject() {

		return gradeSubject;

	}

	public void setDateHeld(Date dateHeld) {
		this.dateHeld = dateHeld;
	}

	public Date getDateHeld() {

		return dateHeld;

	}

	public void setAverage(Integer average) {
		this.average = average;
	}

	public Integer getAverage() {

		return average;

	}

	@Transient
	public String getDisplayName() {
		try {
			return exam.name + " " + gradeSubject.getSubject().getName();
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

		listSearchableFields.add("examScores.examName");

		listSearchableFields.add("examScores.subject");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getExam() != null)
			builder.append("exam:" + getExam().getDisplayName() + " ");

		if (getGradeSubject() != null)
			builder.append("gradeSubject:" + getGradeSubject().getDisplayName()
					+ " ");

		for (BusinessEntity e : examScores) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
