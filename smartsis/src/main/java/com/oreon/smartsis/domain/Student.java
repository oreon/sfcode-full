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
import org.jboss.seam.security.permission.PermissionCheck;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.smartsis.ProjectUtils;

@Entity
@Table(name = "student")
@Filter(name = "archiveFilterDef")
@Name("student")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Student extends com.oreon.smartsis.domain.Person
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = 564371639L;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "picture_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "picture_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "picture_data", length = 4194304))})
	protected FileAttachment picture = new FileAttachment();

	@NotNull
	@Column(name = "gender", unique = false)
	protected com.oreon.smartsis.Gender gender

	;

	@NotNull
	@Column(name = "dateOfBirth", unique = false)
	protected Date dateOfBirth

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id", nullable = true, updatable = true)
	@ContainedIn
	protected Grade grade

	;

	@Transient
	protected Integer age

	;

	protected Double scholarship

	;

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "student_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<StudentVitalInfo> studentVitalInfos = new HashSet<StudentVitalInfo>();

	public void addStudentVitalInfos(StudentVitalInfo studentVitalInfos) {
		studentVitalInfos.setStudent(this);
		this.studentVitalInfos.add(studentVitalInfos);
	}

	@Transient
	public List<com.oreon.smartsis.domain.StudentVitalInfo> getListStudentVitalInfos() {
		return new ArrayList<com.oreon.smartsis.domain.StudentVitalInfo>(
				studentVitalInfos);
	}

	//JSF Friendly function to get count of collections
	public int getStudentVitalInfosCount() {
		return studentVitalInfos.size();
	}

	protected Integer rollNumber

	;

	@Column(name = "discontinueDate", unique = false)
	protected Date discontinueDate

	= new Date();

	protected DiscontinueReason discontinueReason

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parentGroup_id", nullable = true, updatable = true)
	@ContainedIn
	protected ParentGroup parentGroup

	;

	public void setPicture(FileAttachment picture) {
		this.picture = picture;
	}

	public FileAttachment getPicture() {

		return picture;

	}

	public void setGender(com.oreon.smartsis.Gender gender) {
		this.gender = gender;
	}

	public com.oreon.smartsis.Gender getGender() {

		return gender;

	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {

		return dateOfBirth;

	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Grade getGrade() {

		return grade;

	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {

		try {
			return DateUtils.calcAge(dateOfBirth);
		} catch (Exception e) {

			return 0;

		}

	}

	public void setScholarship(Double scholarship) {
		this.scholarship = scholarship;
	}

	public Double getScholarship() {

		return scholarship;

	}

	public void setStudentVitalInfos(Set<StudentVitalInfo> studentVitalInfos) {
		this.studentVitalInfos = studentVitalInfos;
	}

	public Set<StudentVitalInfo> getStudentVitalInfos() {
		return studentVitalInfos;
	}

	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}

	public Integer getRollNumber() {

		return rollNumber;

	}

	public void setDiscontinueDate(Date discontinueDate) {
		this.discontinueDate = discontinueDate;
	}

	public Date getDiscontinueDate() {

		return discontinueDate;

	}

	public void setDiscontinueReason(DiscontinueReason discontinueReason) {
		this.discontinueReason = discontinueReason;
	}

	public DiscontinueReason getDiscontinueReason() {

		return discontinueReason;

	}

	public void setParentGroup(ParentGroup parentGroup) {
		this.parentGroup = parentGroup;
	}

	public ParentGroup getParentGroup() {

		return parentGroup;

	}

	@Transient
	    public String getDisplayName(){
	    	try {
				return super.getDisplayName() + " " + grade.name + " " + getId();
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

		if (getParentGroup() != null)
			builder.append("parentGroup:" + getParentGroup().getDisplayName()
					+ " ");

		for (BusinessEntity e : studentVitalInfos) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
		
		//PermissionCheck pc;
		//pc.re
	}

}
