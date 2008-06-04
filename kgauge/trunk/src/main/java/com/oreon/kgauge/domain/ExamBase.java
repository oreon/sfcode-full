
/**
 * This is generated code - to edit code or override methods use - Exam class
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
public abstract class ExamBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String examNumber;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String description;

	protected Integer questions;

	protected Integer duration;

	protected Double price;

	protected ScoringType scoringStrategy;

	protected ExamStatus examStatus;

	/* Default Constructor */
	public ExamBase() {
	}

	/* Constructor with all attributes */
	public ExamBase(String examNumber, String name, String description,
			Integer questions, Integer duration, Double price,
			ScoringType scoringStrategy, ExamStatus examStatus) {
		this.examNumber = examNumber;
		this.name = name;
		this.description = description;
		this.questions = questions;
		this.duration = duration;
		this.price = price;
		this.scoringStrategy = scoringStrategy;
		this.examStatus = examStatus;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getExamNumber() {

		return this.examNumber;
	}

	@Column(nullable = false, unique = false)
	/*
	Name of the exam e.g. Hibernate Proficiency Exam  
	 */
	public String getName() {

		return this.name;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getDescription() {

		return this.description;
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

	/*
	
	 */
	public ExamStatus getExamStatus() {
		return this.examStatus;
	}

	public void setExamNumber(String examNumber) {
		this.examNumber = examNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setExamStatus(ExamStatus examStatus) {
		this.examStatus = examStatus;
	}

	private com.oreon.kgauge.domain.Category category;

	private com.oreon.kgauge.domain.ExamCreator examCreator;

	private java.util.Set<com.oreon.kgauge.domain.Section> section = new java.util.HashSet<com.oreon.kgauge.domain.Section>();

	@ManyToOne
	@JoinColumn(name = "category_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.Category getCategory() {
		return this.category;
	}

	public void setCategory(com.oreon.kgauge.domain.Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "examCreator_ID", nullable = false, updatable = false)
	@XmlTransient
	public com.oreon.kgauge.domain.ExamCreator getExamCreator() {
		return this.examCreator;
	}

	public void setExamCreator(com.oreon.kgauge.domain.ExamCreator examCreator) {
		this.examCreator = examCreator;
	}

	public void add(com.oreon.kgauge.domain.Section section) {
		section.setExam(examInstance());
		this.section.add(section);
	}

	public void remove(com.oreon.kgauge.domain.Section section) {
		this.section.remove(section);
	}

	@OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_ID", nullable = false)
	public java.util.Set<com.oreon.kgauge.domain.Section> getSection() {
		return this.section;
	}

	public void setSection(
			java.util.Set<com.oreon.kgauge.domain.Section> section) {
		this.section = section;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.Section> getSectionIterator() {
		return this.section.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getSectionCount() {
		return this.section.size();
	}

	public abstract Exam examInstance();

	@Transient
	public String getDisplayName() {
		return examNumber + "";
	}

}
