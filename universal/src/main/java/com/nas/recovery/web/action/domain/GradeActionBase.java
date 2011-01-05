package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.Grade;

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

import com.oreon.tapovan.domain.Student;
import com.oreon.tapovan.domain.GradeSubject;

public abstract class GradeActionBase extends BaseAction<Grade>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Grade grade;

	@In(create = true, value = "studentAction")
	com.nas.recovery.web.action.domain.StudentAction studentsAction;

	@DataModel
	private List<Grade> gradeRecordList;

	public void setGradeId(Long id) {
		if (id == 0) {
			clearInstance();
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
		return new Grade();
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
		loadAssociations();
	}

	@Override
	public Class<Grade> getEntityClass() {
		return Grade.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListStudents();

		initListGradeSubjects();

		initListExams();

	}

	public void updateAssociations() {

		com.oreon.tapovan.domain.Student student = (com.oreon.tapovan.domain.Student) org.jboss.seam.Component
				.getInstance("student");
		student.setGrade(grade);
		events.raiseTransactionSuccessEvent("archivedStudent");

	}

	protected List<com.oreon.tapovan.domain.Student> listStudents = new ArrayList<com.oreon.tapovan.domain.Student>();

	void initListStudents() {

		if (listStudents.isEmpty())
			listStudents.addAll(getInstance().getStudents());

	}

	public List<com.oreon.tapovan.domain.Student> getListStudents() {

		prePopulateListStudents();
		return listStudents;
	}

	public void prePopulateListStudents() {
	}

	public void setListStudents(
			List<com.oreon.tapovan.domain.Student> listStudents) {
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

	protected List<com.oreon.tapovan.domain.GradeSubject> listGradeSubjects = new ArrayList<com.oreon.tapovan.domain.GradeSubject>();

	void initListGradeSubjects() {

		if (listGradeSubjects.isEmpty())
			listGradeSubjects.addAll(getInstance().getGradeSubjects());

	}

	public List<com.oreon.tapovan.domain.GradeSubject> getListGradeSubjects() {

		prePopulateListGradeSubjects();
		return listGradeSubjects;
	}

	public void prePopulateListGradeSubjects() {
	}

	public void setListGradeSubjects(
			List<com.oreon.tapovan.domain.GradeSubject> listGradeSubjects) {
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

	protected List<com.oreon.tapovan.domain.Exam> listExams = new ArrayList<com.oreon.tapovan.domain.Exam>();

	void initListExams() {

		if (listExams.isEmpty())
			listExams.addAll(getInstance().getExams());

	}

	public List<com.oreon.tapovan.domain.Exam> getListExams() {

		prePopulateListExams();
		return listExams;
	}

	public void prePopulateListExams() {
	}

	public void setListExams(List<com.oreon.tapovan.domain.Exam> listExams) {
		this.listExams = listExams;
	}

	protected List<com.oreon.tapovan.domain.Exam> listAvailableExams = new ArrayList<com.oreon.tapovan.domain.Exam>();

	void initListAvailableExams() {

		listAvailableExams = getEntityManager().createQuery(
				"select r from Exam r").getResultList();
		listAvailableExams.removeAll(getInstance().getExams());

	}

	public List<com.oreon.tapovan.domain.Exam> getListAvailableExams() {
		initListAvailableExams();
		prePopulateListAvailableExams();
		return listAvailableExams;
	}

	public void prePopulateListAvailableExams() {
	}

	public void setListAvailableExams(
			List<com.oreon.tapovan.domain.Exam> listAvailableExams) {
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

}
