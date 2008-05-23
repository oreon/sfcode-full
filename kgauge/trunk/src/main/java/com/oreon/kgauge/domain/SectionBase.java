
/**
 * This is generated code - to edit code or override methods use - Section class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

@MappedSuperclass
@Indexed
//@Analyzer(impl = example.EnglishAnalyzer.class)
public abstract class SectionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index=Index.TOKENIZED, store=Store.NO)
	protected String name;

	/* Default Constructor */
	public SectionBase() {
	}

	/* Constructor with all attributes */
	public SectionBase(String name) {
		this.name = name;
	}

	/*
	
	 */
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private com.oreon.kgauge.domain.Exam exam = new com.oreon.kgauge.domain.Exam();

	private java.util.Set<com.oreon.kgauge.domain.Question> question = new java.util.HashSet<com.oreon.kgauge.domain.Question>();

	@ManyToOne
	@JoinColumn(name = "exam_ID", nullable = false, updatable = true)
	@XmlTransient
	@ContainedIn
	public com.oreon.kgauge.domain.Exam getExam() {
		return this.exam;
	}

	public void setExam(com.oreon.kgauge.domain.Exam exam) {
		this.exam = exam;
	}

	public void add(com.oreon.kgauge.domain.Question question) {
		question.setSection(sectionInstance());
		this.question.add(question);
	}

	public void remove(com.oreon.kgauge.domain.Question question) {
		this.question.remove(question);
	}

	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "section_ID", nullable = true)
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

	public abstract Section sectionInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
