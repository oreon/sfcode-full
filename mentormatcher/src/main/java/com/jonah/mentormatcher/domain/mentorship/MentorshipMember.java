package com.jonah.mentormatcher.domain.mentorship;

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

import com.jonah.mentormatcher.ProjectUtils;

@Entity
@Table(name = "mentorshipmember")
@Filter(name = "archiveFilterDef")
@Name("mentorshipMember")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class MentorshipMember extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -1257877902L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "mentorship_id", nullable = false, updatable = true)
	@ContainedIn
	protected Mentorship mentorship

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.jonah.mentormatcher.domain.Employee employee

	;

	public void setMentorship(Mentorship mentorship) {
		this.mentorship = mentorship;
	}

	public Mentorship getMentorship() {

		return mentorship;

	}

	public void setEmployee(com.jonah.mentormatcher.domain.Employee employee) {
		this.employee = employee;
	}

	public com.jonah.mentormatcher.domain.Employee getEmployee() {

		return employee;

	}

	@Transient
	public String getDisplayName() {
		try {
			return mentorship + "";
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

		if (getMentorship() != null)
			builder.append("mentorship:" + getMentorship().getDisplayName()
					+ " ");

		if (getEmployee() != null)
			builder.append("employee:" + getEmployee().getDisplayName() + " ");

		return builder.toString();
	}

}
