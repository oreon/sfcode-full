
/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import java.util.Date;

import org.apache.commons.collections.ListUtils;
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
public abstract class CandidateBase extends Person
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public CandidateBase() {
	}

	private java.util.Set<com.oreon.kgauge.domain.ExamInstance> examInstance = new java.util.HashSet<com.oreon.kgauge.domain.ExamInstance>();

	private com.oreon.kgauge.domain.User user = new com.oreon.kgauge.domain.User();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.User getUser() {
		return this.user;
	}

	public void setUser(com.oreon.kgauge.domain.User user) {
		this.user = user;
	}

	public void addExamInstance(
			com.oreon.kgauge.domain.ExamInstance examInstance) {
		checkMaximumExamInstance();
		examInstance.setCandidate(candidateInstance());
		this.examInstance.add(examInstance);
	}

	public void remove(com.oreon.kgauge.domain.ExamInstance examInstance) {
		this.examInstance.remove(examInstance);
	}

	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "candidate_ID", nullable = true)
	public java.util.Set<com.oreon.kgauge.domain.ExamInstance> getExamInstance() {
		return this.examInstance;
	}

	public void setExamInstance(
			java.util.Set<com.oreon.kgauge.domain.ExamInstance> examInstance) {
		this.examInstance = examInstance;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.ExamInstance> getExamInstanceIterator() {
		return this.examInstance.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getExamInstanceCount() {
		return this.examInstance.size();
	}
	
	/**
	 * @return
	 */
	@Transient
	public List<ExamInstance> getExamInstaceAsList(){
		List<ExamInstance> examInstanceList = new ArrayList<ExamInstance>();
		examInstanceList.addAll(getExamInstance());
		return examInstanceList;
	}

	public void checkMaximumExamInstance() {
		// if(examInstance.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + examInstance );
	}

	/**
	 * <query name="examsTakenByCandidate" retType="List<ExamInstance>">
	select e from ExamInstance e where e.candidate.id = ?1
	</query>
	 */

	public java.util.List findExamInstances(Long candidateId) {
		return null;
	}

	/**
	 * ${query}=select e from ExamInstance e where e.candidate.id = ?1 and sum(e.answeredQuestion.answerChoice) >= e.exam.passMarks
	 */

	public Long findNumberOfCertifications() {
		return null;
	}

	public abstract Candidate candidateInstance();

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
