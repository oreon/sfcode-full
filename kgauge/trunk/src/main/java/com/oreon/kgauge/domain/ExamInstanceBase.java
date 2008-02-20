
/**
 * This is generated code - to edit code or override methods use - ExamInstance class
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
@Table(name="ExamInstance",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
/* 
	
	There are 0 constraints.
 */
public abstract class ExamInstanceBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	//named queries : 0

	private static final long serialVersionUID = 1L;

	private com.oreon.kgauge.domain.Candidate candidate;

	private com.oreon.kgauge.domain.Exam exam;

	private com.oreon.kgauge.domain.Question question;

	public void setCandidate(com.oreon.kgauge.domain.Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne
	@JoinColumn(name = "candidate_ID", nullable = false)
	public com.oreon.kgauge.domain.Candidate getCandidate() {
		return this.candidate;
	}

	public void setExam(com.oreon.kgauge.domain.Exam exam) {
		this.exam = exam;
	}

	@ManyToOne
	@JoinColumn(name = "exam_ID", nullable = false)
	public com.oreon.kgauge.domain.Exam getExam() {
		return this.exam;
	}

	public void setQuestion(com.oreon.kgauge.domain.Question question) {
		this.question = question;
	}

	@ManyToOne
	@JoinColumn(name = "question_ID", nullable = true)
	public com.oreon.kgauge.domain.Question getQuestion() {
		return this.question;
	}

	public abstract ExamInstance examInstanceInstance();

}
