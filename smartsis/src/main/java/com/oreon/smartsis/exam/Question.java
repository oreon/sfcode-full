package com.oreon.smartsis.exam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

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
import org.hibernate.search.annotations.Boost;
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
@Table(name = "question")
@Filter(name = "archiveFilterDef")
@Name("question")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Question extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -277983865L;

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String question

	;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "question_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Choice> choices = new HashSet<Choice>();

	public void addChoice(Choice choice) {
		choice.setQuestion(this);
		this.choices.add(choice);
	}

	@Transient
	public List<com.oreon.smartsis.exam.Choice> getListChoices() {
		return new ArrayList<com.oreon.smartsis.exam.Choice>(choices);
	}

	//JSF Friendly function to get count of collections
	public int getChoicesCount() {
		return choices.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "electronicExam_id", nullable = false, updatable = true)
	@ContainedIn
	protected ElectronicExam electronicExam

	;

	@NotNull
	@Column(name = "correctChoice", unique = false)
	protected ChoiceIndex correctChoice

	;

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {

		return question;

	}

	public void setChoices(Set<Choice> choices) {
		this.choices = choices;
	}

	public Set<Choice> getChoices() {
		return choices;
	}

	public void setElectronicExam(ElectronicExam electronicExam) {
		this.electronicExam = electronicExam;
	}

	public ElectronicExam getElectronicExam() {

		return electronicExam;

	}

	public void setCorrectChoice(ChoiceIndex correctChoice) {
		this.correctChoice = correctChoice;
	}

	public ChoiceIndex getCorrectChoice() {

		return correctChoice;

	}

	@Transient
	public String getDisplayName() {
		try {
			return question + "";
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

		listSearchableFields.add("question");

		listSearchableFields.add("choices.choice");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getQuestion() + " ");

		if (getElectronicExam() != null)
			builder.append("electronicExam:"
					+ getElectronicExam().getDisplayName() + " ");

		for (BusinessEntity e : choices) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
