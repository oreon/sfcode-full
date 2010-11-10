package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Exam;

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

import org.wc.trackrite.exams.Question;

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
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setExamIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getExamId() {
		return (Long) getId();
	}

	//@Factory("examRecordList")
	//@Observer("archivedExam")
	public void findRecords() {
		//search();
	}

	public Exam getEntity() {
		return exam;
	}

	@Override
	public void setEntity(Exam t) {
		this.exam = t;
		loadAssociations();
	}

	public Exam getExam() {
		return getInstance();
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
		return isIdDefined() ? getInstance() : null;
	}

	public void setExam(Exam t) {
		this.exam = t;
		loadAssociations();
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}

	@Override
	public void setEntityList(List<Exam> list) {
		this.examRecordList = list;
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

	protected List<org.wc.trackrite.exams.Question> listQuestions;

	void initListQuestions() {
		listQuestions = new ArrayList<org.wc.trackrite.exams.Question>();

		if (getInstance().getQuestions().isEmpty()) {

		} else
			listQuestions.addAll(getInstance().getQuestions());

	}

	public List<org.wc.trackrite.exams.Question> getListQuestions() {
		if (listQuestions == null)
			initListQuestions();
		return listQuestions;
	}

	public void setListQuestions(
			List<org.wc.trackrite.exams.Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public void deleteQuestions(int index) {
		listQuestions.remove(index);
	}

	@Begin(join = true)
	public void addQuestions() {
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

	public List<Exam> getEntityList() {
		if (examRecordList == null) {
			findRecords();
		}
		return examRecordList;
	}

}
