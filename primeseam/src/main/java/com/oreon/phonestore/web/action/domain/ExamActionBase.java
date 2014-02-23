package com.oreon.phonestore.web.action.domain;

import com.oreon.phonestore.domain.Exam;

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
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.phonestore.domain.Question;

//
public abstract class ExamActionBase extends BaseAction<Exam>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long examId;

	public void setExamId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setExamIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getExamId() {
		return (Long) getId();
	}

	public Exam getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Exam t) {
		this.instance = t;
		loadAssociations();
	}

	public Exam getExam() {
		return (Exam) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('exam', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('exam', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Exam createInstance() {
		Exam instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}

	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

		if (isNew() && instance.getQuestions().isEmpty()) {
			for (int i = 0; i < 1; i++)
				getListQuestions().add(
						new com.oreon.phonestore.domain.Question());
		}

	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Exam getDefinedInstance() {
		return (Exam) (isIdDefined() ? getInstance() : null);
	}

	public void setExam(Exam t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setExamId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}

	public com.oreon.phonestore.domain.Exam findByUnqTitle(String title) {
		return executeSingleResultNamedQuery("exam.findByUnqTitle", title);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListQuestions();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.phonestore.domain.Question> listQuestions = new ArrayList<com.oreon.phonestore.domain.Question>();

	void initListQuestions() {

		if (listQuestions.isEmpty())
			listQuestions.addAll(getInstance().getQuestions());

	}

	public List<com.oreon.phonestore.domain.Question> getListQuestions() {

		prePopulateListQuestions();
		return listQuestions;
	}

	public void prePopulateListQuestions() {
	}

	public void setListQuestions(
			List<com.oreon.phonestore.domain.Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public void deleteQuestions(int index) {
		listQuestions.remove(index);
	}

	@Begin(join = true)
	public void addQuestions() {

		initListQuestions();
		Question questions = new Question();

		questions.setExam(getInstance());

		getListQuestions().add(questions);

	}

	public void updateComposedAssociations() {

		if (listQuestions != null) {

			java.util.Set<Question> items = getInstance().getQuestions();
			for (Question item : items) {
				if (!listQuestions.contains(item))
					getEntityManager().remove(item);
			}

			getInstance().getQuestions().clear();
			getInstance().getQuestions().addAll(listQuestions);
		}
	}

	public void clearLists() {
		listQuestions.clear();

	}

	public String viewExam() {
		load(currentEntityId);
		return "viewExam";
	}

}
