
/**
 * This is generated code - to edit code or override methods use - AnswerChoice class
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
public abstract class AnswerChoiceBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected String answerText;

	protected Integer score;

	/* Default Constructor */
	public AnswerChoiceBase() {
	}

	/* Constructor with all attributes */
	public AnswerChoiceBase(String answerText, Integer score) {
		this.answerText = answerText;
		this.score = score;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getAnswerText() {

		return this.answerText;
	}

	/*
	
	 */
	public Integer getScore() {
		return this.score;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	private com.oreon.kgauge.domain.Question question = new com.oreon.kgauge.domain.Question();

	@ManyToOne
	@JoinColumn(name = "question_ID", nullable = false)
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

}
