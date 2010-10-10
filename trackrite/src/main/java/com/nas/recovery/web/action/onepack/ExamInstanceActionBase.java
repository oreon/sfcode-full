package com.nas.recovery.web.action.onepack;

import org.wc.trackrite.onepack.ExamInstance;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.onepack.Answer;

public abstract class ExamInstanceActionBase extends BaseAction<ExamInstance>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ExamInstance examInstance;

	@In(create = true, value = "examAction")
	com.nas.recovery.web.action.onepack.ExamAction examAction;

	@In(create = true, value = "candidateAction")
	com.nas.recovery.web.action.onepack.CandidateAction candidateAction;

	@DataModel
	private List<ExamInstance> examInstanceRecordList;

	public void setExamInstanceId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	public void setExamId(Long id) {
		if (id != null && id > 0)
			getInstance().setExam(examAction.loadFromId(id));
	}

	public Long getExamId() {
		if (getInstance().getExam() != null)
			return getInstance().getExam().getId();
		return 0L;
	}
	public void setCandidateId(Long id) {
		if (id != null && id > 0)
			getInstance().setCandidate(candidateAction.loadFromId(id));
	}

	public Long getCandidateId() {
		if (getInstance().getCandidate() != null)
			return getInstance().getCandidate().getId();
		return 0L;
	}

	public Long getExamInstanceId() {
		return (Long) getId();
	}

	//@Factory("examInstanceRecordList")
	//@Observer("archivedExamInstance")
	public void findRecords() {
		//search();
	}

	public ExamInstance getEntity() {
		return examInstance;
	}

	@Override
	public void setEntity(ExamInstance t) {
		this.examInstance = t;
		loadAssociations();
	}

	public ExamInstance getExamInstance() {
		return getInstance();
	}

	@Override
	protected ExamInstance createInstance() {
		return new ExamInstance();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		org.wc.trackrite.onepack.Exam exam = examAction.getDefinedInstance();
		if (exam != null) {
			getInstance().setExam(exam);
		}
		org.wc.trackrite.onepack.Candidate candidate = candidateAction
				.getDefinedInstance();
		if (candidate != null) {
			getInstance().setCandidate(candidate);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ExamInstance getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setExamInstance(ExamInstance t) {
		this.examInstance = t;
		loadAssociations();
	}

	@Override
	public Class<ExamInstance> getEntityClass() {
		return ExamInstance.class;
	}

	@Override
	public void setEntityList(List<ExamInstance> list) {
		this.examInstanceRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (examInstance.getExam() != null) {
			criteria = criteria.add(Restrictions.eq("exam.id", examInstance
					.getExam().getId()));
		}

		if (examInstance.getCandidate() != null) {
			criteria = criteria.add(Restrictions.eq("candidate.id",
					examInstance.getCandidate().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (examInstance.getExam() != null) {
			examAction.setInstance(getInstance().getExam());
		}

		if (examInstance.getCandidate() != null) {
			candidateAction.setInstance(getInstance().getCandidate());
		}

		initListAnswers();

	}

	public void updateAssociations() {

	}

	protected List<org.wc.trackrite.onepack.Answer> listAnswers;

	void initListAnswers() {
		listAnswers = new ArrayList<org.wc.trackrite.onepack.Answer>();

		if (getInstance().getAnswers().isEmpty()) {

		} else
			listAnswers.addAll(getInstance().getAnswers());

	}

	public List<org.wc.trackrite.onepack.Answer> getListAnswers() {
		if (listAnswers == null)
			initListAnswers();
		return listAnswers;
	}

	public void setListAnswers(List<org.wc.trackrite.onepack.Answer> listAnswers) {
		this.listAnswers = listAnswers;
	}

	public void deleteAnswers(int index) {
		listAnswers.remove(index);
	}

	@Begin(join = true)
	public void addAnswers() {
		Answer answers = new Answer();

		answers.setExamInstance(getInstance());

		getListAnswers().add(answers);
	}

	public void updateComposedAssociations() {

		if (listAnswers != null) {
			getInstance().getAnswers().clear();
			getInstance().getAnswers().addAll(listAnswers);
		}
	}

	public List<ExamInstance> getEntityList() {
		if (examInstanceRecordList == null) {
			findRecords();
		}
		return examInstanceRecordList;
	}

}
