package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.ExamInstance;

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

import com.oreon.smartsis.domain.ExamScore;

public abstract class ExamInstanceActionBase extends BaseAction<ExamInstance>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ExamInstance examInstance;

	@In(create = true, value = "examAction")
	com.oreon.smartsis.web.action.domain.ExamAction examAction;

	@In(create = true, value = "gradeSubjectAction")
	com.oreon.smartsis.web.action.domain.GradeSubjectAction gradeSubjectAction;

	@DataModel
	private List<ExamInstance> examInstanceRecordList;

	public void setExamInstanceId(Long id) {
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
	public void setExamInstanceIdForModalDlg(Long id) {
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

	public void setGradeSubjectId(Long id) {

		if (id != null && id > 0)
			getInstance().setGradeSubject(gradeSubjectAction.loadFromId(id));

	}

	public Long getGradeSubjectId() {
		if (getInstance().getGradeSubject() != null)
			return getInstance().getGradeSubject().getId();
		return 0L;
	}

	public Long getExamInstanceId() {
		return (Long) getId();
	}

	public ExamInstance getEntity() {
		return examInstance;
	}

	//@Override
	public void setEntity(ExamInstance t) {
		this.examInstance = t;
		loadAssociations();
	}

	public ExamInstance getExamInstance() {
		return (ExamInstance) getInstance();
	}

	@Override
	protected ExamInstance createInstance() {
		ExamInstance instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.domain.Exam exam = examAction.getDefinedInstance();
		if (exam != null && isNew()) {
			getInstance().setExam(exam);
		}

		com.oreon.smartsis.domain.GradeSubject gradeSubject = gradeSubjectAction
				.getDefinedInstance();
		if (gradeSubject != null && isNew()) {
			getInstance().setGradeSubject(gradeSubject);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ExamInstance getDefinedInstance() {
		return (ExamInstance) (isIdDefined() ? getInstance() : null);
	}

	public void setExamInstance(ExamInstance t) {
		this.examInstance = t;
		if (examInstance != null)
			setExamInstanceId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<ExamInstance> getEntityClass() {
		return ExamInstance.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (examInstance.getExam() != null) {
			criteria = criteria.add(Restrictions.eq("exam.id", examInstance
					.getExam().getId()));
		}

		if (examInstance.getGradeSubject() != null) {
			criteria = criteria.add(Restrictions.eq("gradeSubject.id",
					examInstance.getGradeSubject().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (examInstance.getExam() != null) {
			examAction.setInstance(getInstance().getExam());
		}

		if (examInstance.getGradeSubject() != null) {
			gradeSubjectAction.setInstance(getInstance().getGradeSubject());
		}

		initListExamScores();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.domain.ExamScore> listExamScores = new ArrayList<com.oreon.smartsis.domain.ExamScore>();

	void initListExamScores() {

		if (listExamScores.isEmpty())
			listExamScores.addAll(getInstance().getExamScores());

	}

	public List<com.oreon.smartsis.domain.ExamScore> getListExamScores() {

		prePopulateListExamScores();
		return listExamScores;
	}

	public void prePopulateListExamScores() {
	}

	public void setListExamScores(
			List<com.oreon.smartsis.domain.ExamScore> listExamScores) {
		this.listExamScores = listExamScores;
	}

	public void deleteExamScores(int index) {
		listExamScores.remove(index);
	}

	@Begin(join = true)
	public void addExamScores() {
		initListExamScores();
		ExamScore examScores = new ExamScore();

		examScores.setExamInstance(getInstance());

		getListExamScores().add(examScores);
	}

	public void updateComposedAssociations() {

		if (listExamScores != null) {
			getInstance().getExamScores().clear();
			getInstance().getExamScores().addAll(listExamScores);
		}
	}

	public void clearLists() {
		listExamScores.clear();

	}

	public String viewExamInstance() {
		load(currentEntityId);
		return "viewExamInstance";
	}

}
