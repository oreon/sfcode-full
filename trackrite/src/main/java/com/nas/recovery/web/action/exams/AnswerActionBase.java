package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Answer;

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

public abstract class AnswerActionBase extends BaseAction<Answer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Answer answer;

	@In(create = true, value = "choiceAction")
	com.nas.recovery.web.action.exams.ChoiceAction choiceAction;

	@In(create = true, value = "examInstanceAction")
	com.nas.recovery.web.action.exams.ExamInstanceAction examInstanceAction;

	@DataModel
	private List<Answer> answerRecordList;

	public void setAnswerId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAnswerIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
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

	public Answer getEntity() {
		return answer;
	}

	//@Override
	public void setEntity(Answer t) {
		this.answer = t;
		loadAssociations();
	}

	public Answer getAnswer() {
		return (Answer) getInstance();
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

		org.wc.trackrite.exams.Choice choice = choiceAction
				.getDefinedInstance();
		if (choice != null && isNew()) {
			getInstance().setChoice(choice);
		}

		org.wc.trackrite.exams.ExamInstance examInstance = examInstanceAction
				.getDefinedInstance();
		if (examInstance != null && isNew()) {
			getInstance().setExamInstance(examInstance);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Answer getDefinedInstance() {
		return (Answer) (isIdDefined() ? getInstance() : null);
	}

	public void setAnswer(Answer t) {
		this.answer = t;
		loadAssociations();
	}

	@Override
	public Class<Answer> getEntityClass() {
		return Answer.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

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

	public void clearLists() {

	}

}
