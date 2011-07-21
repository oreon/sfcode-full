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
@Table(name = "gradeattendance")
@Filter(name = "archiveFilterDef")
@Name("gradeAttendance")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class GradeAttendance extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = -1796933417L;

	@OneToMany(mappedBy = "gradeAttendance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "gradeAttendance_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Attendance> attendances = new HashSet<Attendance>();

	public void addAttendances(Attendance attendances) {
		attendances.setGradeAttendance(this);
		this.attendances.add(attendances);
	}

	@Transient
	public List<com.oreon.smartsis.attendance.Attendance> getListAttendances() {
		return new ArrayList<com.oreon.smartsis.attendance.Attendance>(
				attendances);
	}

	//JSF Friendly function to get count of collections
	public int getAttendancesCount() {
		return attendances.size();
	}

	@Column(name = "date", unique = false)
	protected Date date = new Date();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.smartsis.domain.Grade grade;

	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Set<Attendance> getAttendances() {
		return attendances;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {

		return date;

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
			return attendances + "";
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

		for (BusinessEntity e : attendances) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
