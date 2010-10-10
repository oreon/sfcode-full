package com.nas.recovery.web.action.onepack;

import org.wc.trackrite.onepack.Answer;

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

public abstract class AnswerActionBase extends BaseAction<Answer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Answer answer;

	@In(create = true, value = "questionAction")
	com.nas.recovery.web.action.onepack.QuestionAction questionAction;

	@In(create = true, value = "choiceAction")
	com.nas.recovery.web.action.onepack.ChoiceAction choiceAction;

	@In(create = true, value = "examInstanceAction")
	com.nas.recovery.web.action.onepack.ExamInstanceAction examInstanceAction;

	@DataModel
	private List<Answer> answerRecordList;

	public void setAnswerId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	public void setQuestionId(Long id) {
		if (id != null && id > 0)
			getInstance().setQuestion(questionAction.loadFromId(id));
	}

	public Long getQuestionId() {
		if (getInstance().getQuestion() != null)
			return getInstance().getQuestion().getId();
		return 0L;
	}
	public void setChoiceId(Long id) {
		if (id != null && id > 0)
			getInstance().setChoice(choiceAction.loadFromId(id));
	}

	public Long getChoiceId() {
		if (getInstance().getChoice() != null)
			return getInstance().getChoice().getId();
		return 0L;
	}
	public void setExamInstanceId(Long id) {
		if (id != null && id > 0)
			getInstance().setExamInstance(examInstanceAction.loadFromId(id));
	}

	public Long getExamInstanceId() {
		if (getInstance().getExamInstance() != null)
			return getInstance().getExamInstance().getId();
		return 0L;
	}

	public Long getAnswerId() {
		return (Long) getId();
	}

	//@Factory("answerRecordList")
	//@Observer("archivedAnswer")
	public void findRecords() {
		//search();
	}

	public Answer getEntity() {
		return answer;
	}

	@Override
	public void setEntity(Answer t) {
		this.answer = t;
		loadAssociations();
	}

	public Answer getAnswer() {
		return getInstance();
	}

	@Override
	protected Answer createInstance() {
		return new Answer();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		org.wc.trackrite.onepack.Question question = questionAction
				.getDefinedInstance();
		if (question != null) {
			getInstance().setQuestion(question);
		}
		org.wc.trackrite.onepack.Choice choice = choiceAction
				.getDefinedInstance();
		if (choice != null) {
			getInstance().setChoice(choice);
		}
		org.wc.trackrite.onepack.ExamInstance examInstance = examInstanceAction
				.getDefinedInstance();
		if (examInstance != null) {
			getInstance().setExamInstance(examInstance);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Answer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setAnswer(Answer t) {
		this.answer = t;
		loadAssociations();
	}

	@Override
	public Class<Answer> getEntityClass() {
		return Answer.class;
	}

	@Override
	public void setEntityList(List<Answer> list) {
		this.answerRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (answer.getQuestion() != null) {
			criteria = criteria.add(Restrictions.eq("question.id", answer
					.getQuestion().getId()));
		}

		if (answer.getChoice() != null) {
			criteria = criteria.add(Restrictions.eq("choice.id", answer
					.getChoice().getId()));
		}

		if (answer.getExamInstance() != null) {
			criteria = criteria.add(Restrictions.eq("examInstance.id", answer
					.getExamInstance().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (answer.getQuestion() != null) {
			questionAction.setInstance(getInstance().getQuestion());
		}

		if (answer.getChoice() != null) {
			choiceAction.setInstance(getInstance().getChoice());
		}

		if (answer.getExamInstance() != null) {
			examInstanceAction.setInstance(getInstance().getExamInstance());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<Answer> getEntityList() {
		if (answerRecordList == null) {
			findRecords();
		}
		return answerRecordList;
	}

}
