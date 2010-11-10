package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Question;

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
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.exams.Choice;

public abstract class QuestionActionBase extends BaseAction<Question>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Question question;

	@In(create = true, value = "examAction")
	com.nas.recovery.web.action.exams.ExamAction examAction;

	@DataModel
	private List<Question> questionRecordList;

	public void setQuestionId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setQuestionIdForModalDlg(Long id) {
		setId(id);
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

	public Long getQuestionId() {
		return (Long) getId();
	}

	//@Factory("questionRecordList")
	//@Observer("archivedQuestion")
	public void findRecords() {
		//search();
	}

	public Question getEntity() {
		return question;
	}

	@Override
	public void setEntity(Question t) {
		this.question = t;
		loadAssociations();
	}

	public Question getQuestion() {
		return getInstance();
	}

	@Override
	protected Question createInstance() {
		return new Question();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		org.wc.trackrite.exams.Exam exam = examAction.getDefinedInstance();
		if (exam != null) {
			getInstance().setExam(exam);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Question getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setQuestion(Question t) {
		this.question = t;
		loadAssociations();
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

	@Override
	public void setEntityList(List<Question> list) {
		this.questionRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (question.getExam() != null) {
			criteria = criteria.add(Restrictions.eq("exam.id", question
					.getExam().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (question.getExam() != null) {
			examAction.setInstance(getInstance().getExam());
		}

		initListChoices();

	}

	public void updateAssociations() {

	}

	protected List<org.wc.trackrite.exams.Choice> listChoices;

	void initListChoices() {
		listChoices = new ArrayList<org.wc.trackrite.exams.Choice>();

		if (getInstance().getChoices().isEmpty()) {
			addChoices();
		} else
			listChoices.addAll(getInstance().getChoices());

	}

	public List<org.wc.trackrite.exams.Choice> getListChoices() {
		if (listChoices == null)
			initListChoices();
		return listChoices;
	}

	public void setListChoices(List<org.wc.trackrite.exams.Choice> listChoices) {
		this.listChoices = listChoices;
	}

	public void deleteChoices(int index) {
		listChoices.remove(index);
	}

	@Begin(join = true)
	public void addChoices() {
		Choice choices = new Choice();

		choices.setQuestion(getInstance());

		getListChoices().add(choices);
	}

	public void updateComposedAssociations() {

		if (listChoices != null) {
			getInstance().getChoices().clear();
			getInstance().getChoices().addAll(listChoices);
		}
	}

	public List<Question> getEntityList() {
		if (questionRecordList == null) {
			findRecords();
		}
		return questionRecordList;
	}

}
