
/**
 * This is generated code - to edit code or override methods use - AnsweredQuestion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;

import org.witchcraft.model.jsf.Image;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
/*@Entity
@Table(name="AnsweredQuestion",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
/* 
	
	There are 0 constraints.
 */
public abstract class AnsweredQuestionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	//named queries : 0

	private static final long serialVersionUID = 1L;

	private com.oreon.kgauge.domain.Question question;

	private java.util.Set<com.oreon.kgauge.domain.AnswerChoice> answerChoice = new java.util.HashSet<com.oreon.kgauge.domain.AnswerChoice>();

	public void setQuestion(com.oreon.kgauge.domain.Question question) {
		this.question = question;
	}

	@ManyToOne
	@JoinColumn(name = "question_ID", nullable = false)
	public com.oreon.kgauge.domain.Question getQuestion() {
		return this.question;
	}

	public void add(com.oreon.kgauge.domain.AnswerChoice answerChoice) {

		this.answerChoice.add(answerChoice);
	}

	public void remove(com.oreon.kgauge.domain.AnswerChoice answerChoice) {
		this.answerChoice.remove(answerChoice);
	}

	@OneToMany
	@JoinColumn(name = "AnsweredQuestion_ID", nullable = true)
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
