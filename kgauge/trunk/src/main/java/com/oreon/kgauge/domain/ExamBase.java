
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

	protected Double price;

	protected ScoringType scoringStrategy;

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getDescription() {

		return this.description;
	}

	@Column(nullable = false, unique = false)
	/*
	Name of the exam e.g. Hibernate Proficiency Exam  
	 */
	public String getName() {

		return this.name;
	}

	/*
	Number of questions in the exam
	 */
	public Integer getQuestions() {
		return this.questions;
	}

	/*
	Duration of exam in minutes
	 */
	public Integer getDuration() {
		return this.duration;
	}

	/*
	
	 */
	public Double getPrice() {
		return this.price;
	}

	/*
	
	 */
	public ScoringType getScoringStrategy() {
		return this.scoringStrategy;
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

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setScoringStrategy(ScoringType scoringStrategy) {
		this.scoringStrategy = scoringStrategy;
	}

	private java.util.Set<com.oreon.kgauge.domain.Question> question = new java.util.HashSet<com.oreon.kgauge.domain.Question>();

	private com.oreon.kgauge.domain.Category category;

	private com.oreon.kgauge.domain.ExamCreator examCreator;

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

	public void add(com.oreon.kgauge.domain.Question question) {

		this.question.add(question);
	}

	public void remove(com.oreon.kgauge.domain.Question question) {
		this.question.remove(question);
	}

	@OneToMany
	@JoinColumn(name = "Exam_ID", nullable = true)
	public java.util.Set<com.oreon.kgauge.domain.Question> getQuestion() {
		return this.question;
	}

	public void setQuestion(
			java.util.Set<com.oreon.kgauge.domain.Question> question) {
		this.question = question;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.Question> getQuestionIterator() {
		return this.question.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getQuestionCount() {
		return this.question.size();
	}

	public abstract Exam examInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
