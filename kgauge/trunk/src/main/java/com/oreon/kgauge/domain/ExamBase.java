
/**
 * This is generated code - to edit code or override methods use - Exam class
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
@Table(name="Exam",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
/* 
	
	There are 0 constraints.
 */
public abstract class ExamBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	//named queries : 0

	private static final long serialVersionUID = 1L;

	protected String description;

	protected String name;

	protected Integer questions;

	protected Integer duration;

	public String getDescription() {
		return this.description;
	}

	@Column(nullable = false, unique = false)
	public String getName() {

		return this.name;
	}

	public Integer getQuestions() {
		return this.questions;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuestions(Integer questions) {
		this.questions = questions;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	private com.oreon.kgauge.domain.Question question;

	private com.oreon.kgauge.domain.Category category;

	private com.oreon.kgauge.domain.ExamCreator examCreator;

	public void setQuestion(com.oreon.kgauge.domain.Question question) {
		this.question = question;
	}

	@ManyToOne
	@JoinColumn(name = "question_ID", nullable = true)
	public com.oreon.kgauge.domain.Question getQuestion() {
		return this.question;
	}

	public void setCategory(com.oreon.kgauge.domain.Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "category_ID", nullable = true)
	public com.oreon.kgauge.domain.Category getCategory() {
		return this.category;
	}

	public void setExamCreator(com.oreon.kgauge.domain.ExamCreator examCreator) {
		this.examCreator = examCreator;
	}

	@ManyToOne
	@JoinColumn(name = "examCreator_ID", nullable = false)
	public com.oreon.kgauge.domain.ExamCreator getExamCreator() {
		return this.examCreator;
	}

	public abstract Exam examInstance();

}
