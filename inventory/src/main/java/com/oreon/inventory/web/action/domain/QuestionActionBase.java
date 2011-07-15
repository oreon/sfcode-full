package com.oreon.inventory.web.action.domain;

import com.oreon.inventory.domain.Question;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class QuestionActionBase extends BaseAction<Question>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Question question;

	@In(create = true, value = "examAction")
	com.oreon.inventory.web.action.domain.ExamAction examAction;

	@DataModel
	private List<Question> questionRecordList;

	public void setQuestionId(Long id) {
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
	public void setQuestionIdForModalDlg(Long id) {
		setId(id);
		clearLists();
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

	public Question getEntity() {
		return question;
	}

	//@Override
	public void setEntity(Question t) {
		this.question = t;
		loadAssociations();
	}

	public Question getQuestion() {
		return (Question) getInstance();
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

		com.oreon.inventory.domain.Exam exam = examAction.getDefinedInstance();
		if (exam != null && isNew()) {
			getInstance().setExam(exam);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Question getDefinedInstance() {
		return (Question) (isIdDefined() ? getInstance() : null);
	}

	public void setQuestion(Question t) {
		this.question = t;
		loadAssociations();
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
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

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
