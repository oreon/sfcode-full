package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.ElectronicExam;

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

import com.oreon.smartsis.exam.Question;

public abstract class ElectronicExamActionBase
		extends
			BaseAction<ElectronicExam> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ElectronicExam electronicExam;

	@In(create = true, value = "gradeSubjectAction")
	com.oreon.smartsis.web.action.domain.GradeSubjectAction gradeSubjectAction;

	@DataModel
	private List<ElectronicExam> electronicExamRecordList;

	public void setElectronicExamId(Long id) {
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
	public void setElectronicExamIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
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

	public Long getElectronicExamId() {
		return (Long) getId();
	}

	public ElectronicExam getEntity() {
		return electronicExam;
	}

	//@Override
	public void setEntity(ElectronicExam t) {
		this.electronicExam = t;
		loadAssociations();
	}

	public ElectronicExam getElectronicExam() {
		return (ElectronicExam) getInstance();
	}

	@Override
	protected ElectronicExam createInstance() {
		ElectronicExam instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.domain.GradeSubject gradeSubject = gradeSubjectAction
				.getDefinedInstance();
		if (gradeSubject != null && isNew()) {
			getInstance().setGradeSubject(gradeSubject);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ElectronicExam getDefinedInstance() {
		return (ElectronicExam) (isIdDefined() ? getInstance() : null);
	}

	public void setElectronicExam(ElectronicExam t) {
		this.electronicExam = t;
		if (electronicExam != null)
			setElectronicExamId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<ElectronicExam> getEntityClass() {
		return ElectronicExam.class;
	}

	public com.oreon.smartsis.exam.ElectronicExam findByUnqName(String name) {
		return executeSingleResultNamedQuery("electronicExam.findByUnqName",
				name);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (electronicExam.getGradeSubject() != null) {
			criteria = criteria.add(Restrictions.eq("gradeSubject.id",
					electronicExam.getGradeSubject().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (electronicExam.getGradeSubject() != null) {
			gradeSubjectAction.setInstance(getInstance().getGradeSubject());
		}

		initListQuestions();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.exam.Question> listQuestions = new ArrayList<com.oreon.smartsis.exam.Question>();

	void initListQuestions() {

		if (listQuestions.isEmpty())
			listQuestions.addAll(getInstance().getQuestions());

	}

	public List<com.oreon.smartsis.exam.Question> getListQuestions() {

		prePopulateListQuestions();
		return listQuestions;
	}

	public void prePopulateListQuestions() {
	}

	public void setListQuestions(
			List<com.oreon.smartsis.exam.Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public void deleteQuestions(int index) {
		listQuestions.remove(index);
	}

	@Begin(join = true)
	public void addQuestions() {
		initListQuestions();
		Question questions = new Question();

		questions.setElectronicExam(getInstance());

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

	public String viewElectronicExam() {
		load(currentEntityId);
		return "viewElectronicExam";
	}

}
