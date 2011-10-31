package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Grade;

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

import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.domain.GradeSubject;

public abstract class GradeActionBase extends BaseAction<Grade>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Grade grade;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentsAction;

	@DataModel
	private List<Grade> gradeRecordList;

	public void setGradeId(Long id) {
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
	public void setGradeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getGradeId() {
		return (Long) getId();
	}

	public Grade getEntity() {
		return grade;
	}

	//@Override
	public void setEntity(Grade t) {
		this.grade = t;
		loadAssociations();
	}

	public Grade getGrade() {
		return (Grade) getInstance();
	}

	@Override
	protected Grade createInstance() {
		Grade instance = super.createInstance();

		return instance;
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

	public Grade getDefinedInstance() {
		return (Grade) (isIdDefined() ? getInstance() : null);
	}

	public void setGrade(Grade t) {
		this.grade = t;
		if (grade != null)
			setGradeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Grade> getEntityClass() {
		return Grade.class;
	}

	public com.oreon.smartsis.domain.Grade findByUnqName(String name) {
		return executeSingleResultNamedQuery("grade.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListStudents();

		initListGradeSubjects();

		initListExams();
		initListAvailableExams();

	}

	public void updateAssociations() {

		com.oreon.smartsis.domain.Student students = (com.oreon.smartsis.domain.Student) org.jboss.seam.Component
				.getInstance("student");
		students.setGrade(grade);
		events.raiseTransactionSuccessEvent("archivedStudent");

	}

	protected List<com.oreon.smartsis.domain.Student> listStudents = new ArrayList<com.oreon.smartsis.domain.Student>();

	void initListStudents() {

		if (listStudents.isEmpty())
			listStudents.addAll(getInstance().getStudents());

	}

	public List<com.oreon.smartsis.domain.Student> getListStudents() {

		prePopulateListStudents();
		return listStudents;
	}

	public void prePopulateListStudents() {
	}

	public void setListStudents(
			List<com.oreon.smartsis.domain.Student> listStudents) {
		this.listStudents = listStudents;
	}

	public void deleteStudents(int index) {
		listStudents.remove(index);
	}

	@Begin(join = true)
	public void addStudents() {
		initListStudents();
		Student students = new Student();

		students.setGrade(getInstance());

		getListStudents().add(students);
	}

	protected List<com.oreon.smartsis.domain.GradeSubject> listGradeSubjects = new ArrayList<com.oreon.smartsis.domain.GradeSubject>();

	void initListGradeSubjects() {

		if (listGradeSubjects.isEmpty())
			listGradeSubjects.addAll(getInstance().getGradeSubjects());

	}

	public List<com.oreon.smartsis.domain.GradeSubject> getListGradeSubjects() {

		prePopulateListGradeSubjects();
		return listGradeSubjects;
	}

	public void prePopulateListGradeSubjects() {
	}

	public void setListGradeSubjects(
			List<com.oreon.smartsis.domain.GradeSubject> listGradeSubjects) {
		this.listGradeSubjects = listGradeSubjects;
	}

	public void deleteGradeSubjects(int index) {
		listGradeSubjects.remove(index);
	}

	@Begin(join = true)
	public void addGradeSubjects() {
		initListGradeSubjects();
		GradeSubject gradeSubjects = new GradeSubject();

		gradeSubjects.setGrade(getInstance());

		getListGradeSubjects().add(gradeSubjects);
	}

	protected List<com.oreon.smartsis.domain.Exam> listExams = new ArrayList<com.oreon.smartsis.domain.Exam>();

	void initListExams() {

		if (listExams.isEmpty())
			listExams.addAll(getInstance().getExams());

	}

	public List<com.oreon.smartsis.domain.Exam> getListExams() {

		prePopulateListExams();
		return listExams;
	}

	public void prePopulateListExams() {
	}

	public void setListExams(List<com.oreon.smartsis.domain.Exam> listExams) {
		this.listExams = listExams;
	}

	protected List<com.oreon.smartsis.domain.Exam> listAvailableExams = new ArrayList<com.oreon.smartsis.domain.Exam>();

	void initListAvailableExams() {

		listAvailableExams = getEntityManager().createQuery(
				"select r from Exam r").getResultList();
		listAvailableExams.removeAll(getInstance().getExams());

	}

	@Begin(join = true)
	public List<com.oreon.smartsis.domain.Exam> getListAvailableExams() {

		prePopulateListAvailableExams();
		return listAvailableExams;
	}

	public void prePopulateListAvailableExams() {
	}

	public void setListAvailableExams(
			List<com.oreon.smartsis.domain.Exam> listAvailableExams) {
		this.listAvailableExams = listAvailableExams;
	}

	public void updateComposedAssociations() {

		if (listStudents != null) {
			getInstance().getStudents().clear();
			getInstance().getStudents().addAll(listStudents);
		}

		if (listGradeSubjects != null) {
			getInstance().getGradeSubjects().clear();
			getInstance().getGradeSubjects().addAll(listGradeSubjects);
		}

		if (listExams != null) {
			getInstance().getExams().clear();
			getInstance().getExams().addAll(listExams);
		}
	}

	public void clearLists() {
		listStudents.clear();
		listGradeSubjects.clear();

		listExams.clear();

	}

	public String viewGrade() {
		load(currentEntityId);
		return "viewGrade";
	}

}
