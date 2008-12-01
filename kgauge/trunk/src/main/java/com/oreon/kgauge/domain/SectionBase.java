
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
public abstract class SectionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	/* Default Constructor */
	public SectionBase() {
	}

	/* Constructor with all attributes */
	public SectionBase(String name) {
		this.name = name;
	}

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
	@ContainedIn
	@XmlTransient
	public com.oreon.kgauge.domain.Exam getExam() {
		return this.exam;
	}

	public void setExam(com.oreon.kgauge.domain.Exam exam) {
		this.exam = exam;
	}

	public void addQuestion(com.oreon.kgauge.domain.Question question) {
		checkMaximumQuestion();
		question.setSection(sectionInstance());
		this.question.add(question);
	}

	public void remove(com.oreon.kgauge.domain.Question question) {
		this.question.remove(question);
	}

	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
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

	public void checkMaximumQuestion() {
		// if(question.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + question );
	}

	public abstract Section sectionInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("name");

		listSearchableFields.add("question.questionText");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
