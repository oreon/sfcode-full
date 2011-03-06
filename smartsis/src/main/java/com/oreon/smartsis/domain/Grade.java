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
@Table(name = "grade")
@Filter(name = "archiveFilterDef")
@Name("grade")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Grade extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -470899813L;

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name;

	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "grade_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Student> students = new HashSet<Student>();

	public void addStudents(Student students) {
		students.setGrade(this);
		this.students.add(students);
	}

	@Transient
	public List<com.oreon.smartsis.domain.Student> getListStudents() {
		return new ArrayList<com.oreon.smartsis.domain.Student>(students);
	}

	//JSF Friendly function to get count of collections
	public int getStudentsCount() {
		return students.size();
	}

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "grades_exams", joinColumns = @JoinColumn(name = "grades_ID"), inverseJoinColumns = @JoinColumn(name = "exams_ID"))
	private Set<Exam> exams = new HashSet<Exam>();

	protected Integer ordinal;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String section;

	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "grade_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<GradeSubject> gradeSubjects = new HashSet<GradeSubject>();

	public void addGradeSubjects(GradeSubject gradeSubjects) {
		gradeSubjects.setGrade(this);
		this.gradeSubjects.add(gradeSubjects);
	}

	@Transient
	public List<com.oreon.smartsis.domain.GradeSubject> getListGradeSubjects() {
		return new ArrayList<com.oreon.smartsis.domain.GradeSubject>(
				gradeSubjects);
	}

	//JSF Friendly function to get count of collections
	public int getGradeSubjectsCount() {
		return gradeSubjects.size();
	}

	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "grade_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<GradeFee> gradeFees = new HashSet<GradeFee>();

	public void addGradeFees(GradeFee gradeFees) {
		gradeFees.setGrade(this);
		this.gradeFees.add(gradeFees);
	}

	@Transient
	public List<com.oreon.smartsis.domain.GradeFee> getListGradeFees() {
		return new ArrayList<com.oreon.smartsis.domain.GradeFee>(gradeFees);
	}

	//JSF Friendly function to get count of collections
	public int getGradeFeesCount() {
		return gradeFees.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public Integer getOrdinal() {

		return ordinal;

	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSection() {

		return section;

	}

	public void setGradeSubjects(Set<GradeSubject> gradeSubjects) {
		this.gradeSubjects = gradeSubjects;
	}

	public Set<GradeSubject> getGradeSubjects() {
		return gradeSubjects;
	}

	public void setGradeFees(Set<GradeFee> gradeFees) {
		this.gradeFees = gradeFees;
	}

	public Set<GradeFee> getGradeFees() {
		return gradeFees;
	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
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

		listSearchableFields.add("name");

		listSearchableFields.add("section");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		builder.append(getSection() + " ");

		for (BusinessEntity e : students) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : gradeSubjects) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : gradeFees) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
