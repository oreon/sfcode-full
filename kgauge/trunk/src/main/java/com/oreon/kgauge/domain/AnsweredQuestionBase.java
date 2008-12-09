
/**
 * This is generated code - to edit code or override methods use - AnsweredQuestion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
@Analyzer(impl = org.witchcraft.lucene.analyzers.EnglishAnalyzer.class)
public abstract class AnsweredQuestionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected boolean isCorrect;

	/* Default Constructor */
	public AnsweredQuestionBase() {
	}

	@Transient
	public boolean isIsCorrect() {

		return answerChoice != null && answerChoice.isCorrectChoice();

	}

	@Transient
	public boolean getIsCorrect() {
		return isIsCorrect();
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	private com.oreon.kgauge.domain.Question question;

	private com.oreon.kgauge.domain.ExamInstance examInstance = new com.oreon.kgauge.domain.ExamInstance();

	private com.oreon.kgauge.domain.AnswerChoice answerChoice;

	@ManyToOne
	@JoinColumn(name = "question_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.Question getQuestion() {
		return this.question;
	}

	public void setQuestion(com.oreon.kgauge.domain.Question question) {
		this.question = question;
	}

	@ManyToOne
	@JoinColumn(name = "examInstance_ID", nullable = false, updatable = true)
	@ContainedIn
	@XmlTransient
	public com.oreon.kgauge.domain.ExamInstance getExamInstance() {
		return this.examInstance;
	}

	public void setExamInstance(
			com.oreon.kgauge.domain.ExamInstance examInstance) {
		this.examInstance = examInstance;
	}

	@ManyToOne
	@JoinColumn(name = "answerChoice_ID", nullable = true, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.AnswerChoice getAnswerChoice() {
		return this.answerChoice;
	}

	public void setAnswerChoice(
			com.oreon.kgauge.domain.AnswerChoice answerChoice) {
		this.answerChoice = answerChoice;
	}

	public abstract AnsweredQuestion answeredQuestionInstance();

	@Transient
	public String getDisplayName() {
		return answerChoice.getDisplayName() + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
