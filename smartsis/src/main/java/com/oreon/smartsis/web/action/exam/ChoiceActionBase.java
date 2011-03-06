package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.Choice;

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

public abstract class ChoiceActionBase extends BaseAction<Choice>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Choice choice;

	@In(create = true, value = "questionAction")
	com.oreon.smartsis.web.action.exam.QuestionAction questionAction;

	@DataModel
	private List<Choice> choiceRecordList;

	public void setChoiceId(Long id) {
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
	public void setChoiceIdForModalDlg(Long id) {
		setId(id);
		clearLists();
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

	public Long getChoiceId() {
		return (Long) getId();
	}

	public Choice getEntity() {
		return choice;
	}

	//@Override
	public void setEntity(Choice t) {
		this.choice = t;
		loadAssociations();
	}

	public Choice getChoice() {
		return (Choice) getInstance();
	}

	@Override
	protected Choice createInstance() {
		return new Choice();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.exam.Question question = questionAction
				.getDefinedInstance();
		if (question != null && isNew()) {
			getInstance().setQuestion(question);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Choice getDefinedInstance() {
		return (Choice) (isIdDefined() ? getInstance() : null);
	}

	public void setChoice(Choice t) {
		this.choice = t;
		loadAssociations();
	}

	@Override
	public Class<Choice> getEntityClass() {
		return Choice.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (choice.getQuestion() != null) {
			criteria = criteria.add(Restrictions.eq("question.id", choice
					.getQuestion().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (choice.getQuestion() != null) {
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
