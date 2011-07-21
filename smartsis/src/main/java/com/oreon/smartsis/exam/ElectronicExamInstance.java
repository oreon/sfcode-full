package com.oreon.smartsis.exam;

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
@Table(name = "electronicexaminstance")
@Filter(name = "archiveFilterDef")
@Name("electronicExamInstance")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ElectronicExamInstance extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = 1166858557L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.smartsis.domain.Student student;

	@OneToMany(mappedBy = "electronicExamInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "electronicExamInstance_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<QuestionInstance> questionInstances = new HashSet<QuestionInstance>();

	public void addQuestionInstances(QuestionInstance questionInstances) {
		questionInstances.setElectronicExamInstance(this);
		this.questionInstances.add(questionInstances);
	}

	@Transient
	public List<com.oreon.smartsis.exam.QuestionInstance> getListQuestionInstances() {
		return new ArrayList<com.oreon.smartsis.exam.QuestionInstance>(
				questionInstances);
	}

	//JSF Friendly function to get count of collections
	public int getQuestionInstancesCount() {
		return questionInstances.size();
	}

	protected Integer score;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "electronicExamEvent_id", nullable = false, updatable = true)
	@ContainedIn
	protected ElectronicExamEvent electronicExamEvent;

	protected Integer timeTaken;

	public void setStudent(com.oreon.smartsis.domain.Student student) {
		this.student = student;
	}

	public com.oreon.smartsis.domain.Student getStudent() {

		return student;

	}

	public void setQuestionInstances(Set<QuestionInstance> questionInstances) {
		this.questionInstances = questionInstances;
	}

	public Set<QuestionInstance> getQuestionInstances() {
		return questionInstances;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScore() {

		return score;

	}

	public void setElectronicExamEvent(ElectronicExamEvent electronicExamEvent) {
		this.electronicExamEvent = electronicExamEvent;
	}

	public ElectronicExamEvent getElectronicExamEvent() {

		return electronicExamEvent;

	}

	public void setTimeTaken(Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Integer getTimeTaken() {

		return timeTaken;

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

		if (getElectronicExamEvent() != null)
			builder.append("electronicExamEvent:"
					+ getElectronicExamEvent().getDisplayName() + " ");

		for (BusinessEntity e : questionInstances) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
