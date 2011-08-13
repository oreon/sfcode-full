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
@Table(name = "parentgroup")
@Filter(name = "archiveFilterDef")
@Name("parentGroup")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ParentGroup extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = -1806682691L;

	@OneToMany(mappedBy = "parentGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "parentGroup_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Student> students = new HashSet<Student>();

	public void addStudents(Student students) {
		students.setParentGroup(this);
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

	@OneToMany(mappedBy = "parentGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "parentGroup_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Parent> parents = new HashSet<Parent>();

	public void addParents(Parent parents) {
		parents.setParentGroup(this);
		this.parents.add(parents);
	}

	@Transient
	public List<com.oreon.smartsis.domain.Parent> getListParents() {
		return new ArrayList<com.oreon.smartsis.domain.Parent>(parents);
	}

	//JSF Friendly function to get count of collections
	public int getParentsCount() {
		return parents.size();
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setParents(Set<Parent> parents) {
		this.parents = parents;
	}

	public Set<Parent> getParents() {
		return parents;
	}

	@Transient
	public String getDisplayName() {
		try {
			return students + "";
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

		for (BusinessEntity e : students) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : parents) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
