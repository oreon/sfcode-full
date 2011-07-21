package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.Question;

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

import com.oreon.smartsis.exam.Choice;

public abstract class QuestionActionBase extends BaseAction<Question>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Question question;

	@In(create = true, value = "electronicExamAction")
	com.oreon.smartsis.web.action.exam.ElectronicExamAction electronicExamAction;

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

	public void setElectronicExamId(Long id) {

		if (id != null && id > 0)
			getInstance()
					.setElectronicExam(electronicExamAction.loadFromId(id));

	}

	public Long getElectronicExamId() {
		if (getInstance().getElectronicExam() != null)
			return getInstance().getElectronicExam().getId();
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

		com.oreon.smartsis.exam.ElectronicExam electronicExam = electronicExamAction
				.getDefinedInstance();
		if (electronicExam != null && isNew()) {
			getInstance().setElectronicExam(electronicExam);
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
		if (question != null)
			setQuestionId(t.getId());
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

		if (question.getElectronicExam() != null) {
			criteria = criteria.add(Restrictions.eq("electronicExam.id",
					question.getElectronicExam().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (question.getElectronicExam() != null) {
			electronicExamAction.setInstance(getInstance().getElectronicExam());
		}

		initListChoices();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.exam.Choice> listChoices = new ArrayList<com.oreon.smartsis.exam.Choice>();

	void initListChoices() {

		if (listChoices.isEmpty())
			listChoices.addAll(getInstance().getChoices());

	}

	public List<com.oreon.smartsis.exam.Choice> getListChoices() {

		prePopulateListChoices();
		return listChoices;
	}

	public void prePopulateListChoices() {
	}

	public void setListChoices(List<com.oreon.smartsis.exam.Choice> listChoices) {
		this.listChoices = listChoices;
	}

	public void deleteChoices(int index) {
		listChoices.remove(index);
	}

	@Begin(join = true)
	public void addChoices() {
		initListChoices();
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

	public void clearLists() {
		listChoices.clear();

	}

}
