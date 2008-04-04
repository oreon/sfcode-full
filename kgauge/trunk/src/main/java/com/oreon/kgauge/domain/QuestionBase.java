
/**
 * This is generated code - to edit code or override methods use - Question class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;

import org.witchcraft.model.jsf.Image;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Set;

@MappedSuperclass
public abstract class QuestionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected String questionText;

	protected DifficultyLevel difficultyLevel;

	/* Default Constructor */
	public QuestionBase() {
	}

	/* Constructor with all attributes */
	public QuestionBase(String questionText, DifficultyLevel difficultyLevel) {
		this.questionText = questionText;
		this.difficultyLevel = difficultyLevel;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getQuestionText() {

		return this.questionText;
	}

	/*
	
	 */
	public DifficultyLevel getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	private java.util.Set<com.oreon.kgauge.domain.AnswerChoice> answerChoice = new java.util.HashSet<com.oreon.kgauge.domain.AnswerChoice>();

	private com.oreon.kgauge.domain.Section section = new com.oreon.kgauge.domain.Section();

	@ManyToOne
	@JoinColumn(name = "section_ID", nullable = false)
	@XmlTransient
	public com.oreon.kgauge.domain.Section getSection() {
		return this.section;
	}

	public void setSection(com.oreon.kgauge.domain.Section section) {
		this.section = section;
	}

	public void add(com.oreon.kgauge.domain.AnswerChoice answerChoice) {
		answerChoice.setQuestion(questionInstance());
		this.answerChoice.add(answerChoice);
	}

	public void remove(com.oreon.kgauge.domain.AnswerChoice answerChoice) {
		this.answerChoice.remove(answerChoice);
	}

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "question_ID", nullable = false)
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

	public abstract Question questionInstance();

	@Transient
	public String getDisplayName() {
		return questionText + "";
	}

}
