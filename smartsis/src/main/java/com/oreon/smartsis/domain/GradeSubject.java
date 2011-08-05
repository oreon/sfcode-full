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
@Table(name = "gradesubject")
@Filter(name = "archiveFilterDef")
@Name("gradeSubject")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class GradeSubject extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = 1350783005L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id", nullable = false, updatable = true)
	@ContainedIn
	protected Subject subject

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", nullable = true, updatable = true)
	@ContainedIn
	protected Employee employee

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id", nullable = false, updatable = true)
	@ContainedIn
	protected Grade grade

	;

	@OneToMany(mappedBy = "gradeSubject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "gradeSubject_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<CourseDocuments> courseDocumentses = new HashSet<CourseDocuments>();

	public void addCourseDocumentses(CourseDocuments courseDocumentses) {
		courseDocumentses.setGradeSubject(this);
		this.courseDocumentses.add(courseDocumentses);
	}

	@Transient
	public List<com.oreon.smartsis.domain.CourseDocuments> getListCourseDocumentses() {
		return new ArrayList<com.oreon.smartsis.domain.CourseDocuments>(
				courseDocumentses);
	}

	//JSF Friendly function to get count of collections
	public int getCourseDocumentsesCount() {
		return courseDocumentses.size();
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Subject getSubject() {

		return subject;

	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {

		return employee;

	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Grade getGrade() {

		return grade;

	}

	public void setCourseDocumentses(Set<CourseDocuments> courseDocumentses) {
		this.courseDocumentses = courseDocumentses;
	}

	public Set<CourseDocuments> getCourseDocumentses() {
		return courseDocumentses;
	}

	@Transient
	public String getDisplayName() {
		try {
			return grade.getName() + " -" + subject.getName();
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

		listSearchableFields.add("courseDocumentses.name");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getSubject() != null)
			builder.append("subject:" + getSubject().getDisplayName() + " ");

		if (getEmployee() != null)
			builder.append("employee:" + getEmployee().getDisplayName() + " ");

		if (getGrade() != null)
			builder.append("grade:" + getGrade().getDisplayName() + " ");

		for (BusinessEntity e : courseDocumentses) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
