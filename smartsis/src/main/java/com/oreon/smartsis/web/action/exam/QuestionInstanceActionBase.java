package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.QuestionInstance;

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

public abstract class QuestionInstanceActionBase
		extends
			BaseAction<QuestionInstance> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private QuestionInstance questionInstance;

	@In(create = true, value = "electronicExamInstanceAction")
	com.oreon.smartsis.web.action.exam.ElectronicExamInstanceAction electronicExamInstanceAction;

	@In(create = true, value = "questionAction")
	com.oreon.smartsis.web.action.exam.QuestionAction questionAction;

	@DataModel
	private List<QuestionInstance> questionInstanceRecordList;

	public void setQuestionInstanceId(Long id) {
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
	public void setQuestionInstanceIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setElectronicExamInstanceId(Long id) {

		if (id != null && id > 0)
			getInstance().setElectronicExamInstance(
					electronicExamInstanceAction.loadFromId(id));

	}

	public Long getElectronicExamInstanceId() {
		if (getInstance().getElectronicExamInstance() != null)
			return getInstance().getElectronicExamInstance().getId();
		return 0L;
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

	public Long getQuestionInstanceId() {
		return (Long) getId();
	}

	public QuestionInstance getEntity() {
		return questionInstance;
	}

	//@Override
	public void setEntity(QuestionInstance t) {
		this.questionInstance = t;
		loadAssociations();
	}

	public QuestionInstance getQuestionInstance() {
		return (QuestionInstance) getInstance();
	}

	@Override
	protected QuestionInstance createInstance() {
		return new QuestionInstance();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.exam.ElectronicExamInstance electronicExamInstance = electronicExamInstanceAction
				.getDefinedInstance();
		if (electronicExamInstance != null && isNew()) {
			getInstance().setElectronicExamInstance(electronicExamInstance);
		}

		com.oreon.smartsis.exam.Question question = questionAction
				.getDefinedInstance();
		if (question != null && isNew()) {
			getInstance().setQuestion(question);
		}

	}

	public boolean isWired() {
		return true;
	}

	public QuestionInstance getDefinedInstance() {
		return (QuestionInstance) (isIdDefined() ? getInstance() : null);
	}

	public void setQuestionInstance(QuestionInstance t) {
		this.questionInstance = t;
		if (questionInstance != null)
			setQuestionInstanceId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<QuestionInstance> getEntityClass() {
		return QuestionInstance.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (questionInstance.getElectronicExamInstance() != null) {
			criteria = criteria.add(Restrictions.eq(
					"electronicExamInstance.id", questionInstance
							.getElectronicExamInstance().getId()));
		}

		if (questionInstance.getQuestion() != null) {
			criteria = criteria.add(Restrictions.eq("question.id",
					questionInstance.getQuestion().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (questionInstance.getElectronicExamInstance() != null) {
			electronicExamInstanceAction.setInstance(getInstance()
					.getElectronicExamInstance());
		}

		if (questionInstance.getQuestion() != null) {
			questionAction.setInstance(getInstance().getQuestion());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
