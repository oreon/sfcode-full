
/**
 * This is generated code - to edit code or override methods use - AnswerChoice class
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
public abstract class AnswerChoiceBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String answerText;

	protected Integer score;

	protected boolean correctChoice;

	/* Default Constructor */
	public AnswerChoiceBase() {
	}

	/* Constructor with all attributes */
	public AnswerChoiceBase(String answerText, Integer score,
			boolean correctChoice) {
		this.answerText = answerText;
		this.score = score;
		this.correctChoice = correctChoice;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getAnswerText() {

		return this.answerText;
	}

	public Integer getScore() {
		return this.score;
	}

	public boolean isCorrectChoice() {
		return this.correctChoice;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setCorrectChoice(boolean correctChoice) {
		this.correctChoice = correctChoice;
	}

	private com.oreon.kgauge.domain.Question question = new com.oreon.kgauge.domain.Question();

	@ManyToOne
	@JoinColumn(name = "question_ID", nullable = false, updatable = true)
	@ContainedIn
	@XmlTransient
	public com.oreon.kgauge.domain.Question getQuestion() {
		return this.question;
	}

	public void setQuestion(com.oreon.kgauge.domain.Question question) {
		this.question = question;
	}

	public abstract AnswerChoice answerChoiceInstance();

	@Transient
	public String getDisplayName() {
		return answerText + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("answerText");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
