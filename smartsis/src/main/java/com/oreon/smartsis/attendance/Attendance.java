package com.oreon.smartsis.attendance;

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
@Table(name = "attendance")
@Filter(name = "archiveFilterDef")
@Name("attendance")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Attendance extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = -1453129684L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.smartsis.domain.Student student

	;

	@NotNull
	@Column(name = "date", unique = false)
	protected Date date

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "gradeSubject_id", nullable = true, updatable = true)
	@ContainedIn
	protected com.oreon.smartsis.domain.GradeSubject gradeSubject

	;

	protected AbsenceCode absenceCode

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "gradeAttendance_id", nullable = false, updatable = true)
	@ContainedIn
	protected GradeAttendance gradeAttendance

	;

	public void setStudent(com.oreon.smartsis.domain.Student student) {
		this.student = student;
	}

	public com.oreon.smartsis.domain.Student getStudent() {

		return student;

	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {

		return date;

	}

	public void setGradeSubject(
			com.oreon.smartsis.domain.GradeSubject gradeSubject) {
		this.gradeSubject = gradeSubject;
	}

	public com.oreon.smartsis.domain.GradeSubject getGradeSubject() {

		return gradeSubject;

	}

	public void setAbsenceCode(AbsenceCode absenceCode) {
		this.absenceCode = absenceCode;
	}

	public AbsenceCode getAbsenceCode() {

		return absenceCode;

	}

	public void setGradeAttendance(GradeAttendance gradeAttendance) {
		this.gradeAttendance = gradeAttendance;
	}

	public GradeAttendance getGradeAttendance() {

		return gradeAttendance;

	}

	@Transient
	public String getDisplayName() {
		try {
			return student + "";
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

		if (getGradeSubject() != null)
			builder.append("gradeSubject:" + getGradeSubject().getDisplayName()
					+ " ");

		if (getGradeAttendance() != null)
			builder.append("gradeAttendance:"
					+ getGradeAttendance().getDisplayName() + " ");

		return builder.toString();
	}

}
