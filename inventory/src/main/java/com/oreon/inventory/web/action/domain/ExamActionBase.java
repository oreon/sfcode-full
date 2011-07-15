package com.oreon.inventory.web.action.domain;

import com.oreon.inventory.domain.Exam;

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

import com.oreon.inventory.domain.Question;

public abstract class ExamActionBase extends BaseAction<Exam>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Exam exam;

	@DataModel
	private List<Exam> examRecordList;

	public void setExamId(Long id) {
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
	public void setExamIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getExamId() {
		return (Long) getId();
	}

	public Exam getEntity() {
		return exam;
	}

	//@Override
	public void setEntity(Exam t) {
		this.exam = t;
		loadAssociations();
	}

	public Exam getExam() {
		return (Exam) getInstance();
	}

	@Override
	protected Exam createInstance() {
		return new Exam();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
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
		this.exam = t;
		loadAssociations();
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}

	public com.oreon.inventory.domain.Exam findByUnqTitle(String title) {
		return executeSingleResultNamedQuery("exam.findByUnqTitle", title);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListQuestions();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.inventory.domain.Question> listQuestions = new ArrayList<com.oreon.inventory.domain.Question>();

	void initListQuestions() {

		if (listQuestions.isEmpty())
			listQuestions.addAll(getInstance().getQuestions());

	}

	public List<com.oreon.inventory.domain.Question> getListQuestions() {

		prePopulateListQuestions();
		return listQuestions;
	}

	public void prePopulateListQuestions() {
	}

	public void setListQuestions(
			List<com.oreon.inventory.domain.Question> listQuestions) {
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
			getInstance().getQuestions().clear();
			getInstance().getQuestions().addAll(listQuestions);
		}
	}

	public void clearLists() {
		listQuestions.clear();

	}

}
