
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

import org.witchcraft.model.jsf.Image;
import java.util.Set;

@MappedSuperclass
@Indexed
//@Analyzer(impl = example.EnglishAnalyzer.class)
public abstract class AnsweredQuestionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public AnsweredQuestionBase() {
	}

	private com.oreon.kgauge.domain.Question question;

	private com.oreon.kgauge.domain.ExamInstance examInstance = new com.oreon.kgauge.domain.ExamInstance();

	private java.util.Set<com.oreon.kgauge.domain.AnswerChoice> answerChoice = new java.util.HashSet<com.oreon.kgauge.domain.AnswerChoice>();

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
	@XmlTransient
	public com.oreon.kgauge.domain.ExamInstance getExamInstance() {
		return this.examInstance;
	}

	public void setExamInstance(
			com.oreon.kgauge.domain.ExamInstance examInstance) {
		this.examInstance = examInstance;
	}

	public void add(com.oreon.kgauge.domain.AnswerChoice answerChoice) {
		this.answerChoice.add(answerChoice);
	}

	public void remove(com.oreon.kgauge.domain.AnswerChoice answerChoice) {
		this.answerChoice.remove(answerChoice);
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "answeredQuestion_ID", nullable = true)
	public java.util.Set<com.oreon.kgauge.domain.AnswerChoice> getAnswerChoice() {
		return this.answerChoice;
	}

	public void setAnswerChoice(
			java.util.Set<com.oreon.kgauge.domain.AnswerChoice> answerChoice) {
		this.answerChoice = answerChoice;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.AnswerChoice> getAnswerChoiceIterator() {
		return this.answerChoice.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getAnswerChoiceCount() {
		return this.answerChoice.size();
	}

	public abstract AnsweredQuestion answeredQuestionInstance();

}
