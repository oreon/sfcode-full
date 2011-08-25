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
@Table(name = "mentorship")
@Filter(name = "archiveFilterDef")
@Name("mentorship")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Mentorship extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1767594924L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "mentor_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.jonah.mentormatcher.domain.Employee mentor

	;

	protected Date startDate

	;

	protected Date endDate

	;

	@OneToMany(mappedBy = "mentorship", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "mentorship_ID", nullable = false)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<MentorshipMember> mentees = new HashSet<MentorshipMember>();

	public void addMentees(MentorshipMember mentees) {
		mentees.setMentorship(this);
		this.mentees.add(mentees);
	}

	@Transient
	public List<com.jonah.mentormatcher.domain.mentorship.MentorshipMember> getListMentees() {
		return new ArrayList<com.jonah.mentormatcher.domain.mentorship.MentorshipMember>(
				mentees);
	}

	//JSF Friendly function to get count of collections
	public int getMenteesCount() {
		return mentees.size();
	}

	public void setMentor(com.jonah.mentormatcher.domain.Employee mentor) {
		this.mentor = mentor;
	}

	public com.jonah.mentormatcher.domain.Employee getMentor() {

		return mentor;

	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {

		return startDate;

	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {

		return endDate;

	}

	public void setMentees(Set<MentorshipMember> mentees) {
		this.mentees = mentees;
	}

	public Set<MentorshipMember> getMentees() {
		return mentees;
	}

	@Transient
	public String getDisplayName() {
		try {
			return mentor + "";
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

		if (getMentor() != null)
			builder.append("mentor:" + getMentor().getDisplayName() + " ");

		for (BusinessEntity e : mentees) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
